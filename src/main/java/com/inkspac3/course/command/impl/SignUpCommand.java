package com.inkspac3.course.command.impl;

import com.inkspac3.course.command.Command;
import com.inkspac3.course.service.UserService;
import com.inkspac3.course.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class SignUpCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirmPassword");
    String role = request.getParameter("role");
    UserService userService = UserServiceImpl.getInstance();

    String page = "pages/register.jsp";
    return page;
  }
}
