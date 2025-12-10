package com.inkspac3.course.servlet;

import com.inkspac3.course.dto.RegisterUserDto;
import com.inkspac3.course.exception.ServiceException;
import com.inkspac3.course.service.UserService;
import com.inkspac3.course.service.impl.UserServiceImpl;
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
    RegisterUserDto dto = new RegisterUserDto();
    dto.setUsername(req.getParameter("username"));
    dto.setEmail(req.getParameter("email"));
    dto.setPassword(req.getParameter("password"));
    dto.setConfirmPassword(req.getParameter("confirmPassword"));

    try {
      userService.register(dto);
      resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");

    } catch (ServiceException e) {
      req.setAttribute("error", e.getMessage());
      req.setAttribute("user", dto);
      req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
      logger.error(e);

    } catch (Exception e) {
      req.setAttribute("error", "Internal server error");
      req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
      logger.error(e);
    }
  }
}



