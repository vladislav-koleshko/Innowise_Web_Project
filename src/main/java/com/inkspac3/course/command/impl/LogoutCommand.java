package com.inkspac3.course.command.impl;

import com.inkspac3.course.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    return "";
  }
}
