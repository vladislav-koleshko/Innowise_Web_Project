package com.inkspac3.course.servlet;

import com.inkspac3.course.model.dto.LoginUserDto;
import com.inkspac3.course.exception.ServiceException;
import com.inkspac3.course.model.User;
import com.inkspac3.course.service.UserService;
import com.inkspac3.course.service.impl.UserServiceImpl;
import com.inkspac3.course.validator.Validator;
import com.inkspac3.course.validator.impl.LoginValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
  public static final Logger logger = LogManager.getLogger();
  private final UserService userService = new UserServiceImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    LoginUserDto userDto = new LoginUserDto();
    userDto.setUsername(req.getParameter("username"));
    userDto.setPassword(req.getParameter("password"));
    Validator<LoginUserDto> validator = new LoginValidator();

    if(!validator.validate(userDto)) {
      req.setAttribute("error", "Invalid username or password");
      req.setAttribute("user", userDto);
      req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
      return;
    }

    try{
      User user = userService.authenticate(userDto);
      req.getSession().setAttribute("user", user);
      req.getSession().setAttribute("role", user.getRole());
      resp.sendRedirect(req.getContextPath() + "/pages/dashboard.jsp");
    } catch (ServiceException e) {
      req.setAttribute("error", e.getMessage());
      req.setAttribute("user", userDto);
      req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
      logger.error(e);

    } catch (Exception e) {
      req.setAttribute("error", "Internal server error");
      req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
      logger.error(e);
    }
  }

}
