package com.inkspac3.course.command;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
  String execute(HttpServletRequest request);
}
