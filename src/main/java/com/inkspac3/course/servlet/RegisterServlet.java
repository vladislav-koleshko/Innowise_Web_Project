package com.inkspac3.course.servlet;

import com.inkspac3.course.dto.RegisterUserDto;
import com.inkspac3.course.exception.ServiceException;
import com.inkspac3.course.service.UserService;
import com.inkspac3.course.service.impl.UserServiceImpl;
import com.inkspac3.course.validator.*;
import com.inkspac3.course.validator.impl.RegistrationValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.*;

import java.io.IOException;

@WebServlet(name = "register", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
  public static final Logger logger = LogManager.getLogger();
  private final UserService userService = new UserServiceImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RegisterUserDto userDto = new RegisterUserDto();
    userDto.setUsername(req.getParameter("username"));
    userDto.setEmail(req.getParameter("email"));
    userDto.setPassword(req.getParameter("password"));
    userDto.setConfirmPassword(req.getParameter("confirmPassword"));
    Validator<RegisterUserDto> validator = new RegistrationValidator();

    if(!validator.validate(userDto)) {
      req.setAttribute("error", "Некорректный формат логина или пароля");
      req.setAttribute("user", userDto);
      req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
      return;
    }

    try {
      userService.register(userDto);
      resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
    } catch (ServiceException e) {
      req.setAttribute("error", e.getMessage());
      req.setAttribute("user", userDto);
      req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
      logger.error(e);
    } catch (Exception e) {
      req.setAttribute("error", "Internal server error");
      req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
      logger.error(e);
    }
  }
}



