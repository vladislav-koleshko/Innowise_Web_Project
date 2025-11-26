package com.inkspac3.course.command.impl;

import com.inkspac3.course.command.Command;
import com.inkspac3.course.service.UserService;
import com.inkspac3.course.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    UserService userService = UserServiceImpl.getInstance();
    String page;
    if(userService.authenticate(username, password)) {
      request.getSession().setAttribute("user", username);
      page = "pages/index.jsp";
    } else {
      request.setAttribute("error", "Invalid username or password");
      page = "pages/auth.jsp";
    }
    return page;
  }
}
