package com.inkspac3.course.command;

import com.inkspac3.course.command.impl.AddUserCommand;
import com.inkspac3.course.command.impl.DefaultCommand;
import com.inkspac3.course.command.impl.LoginCommand;
import com.inkspac3.course.command.impl.LogoutCommand;

public enum CommandType {
  ADD_USER(new AddUserCommand()),
  LOGIN(new LoginCommand()),
  LOGOUT(new LogoutCommand()),
  DEFAULT(new DefaultCommand());
  Command command;

  CommandType(Command command) {
    this.command = command;
  }

  public static Command define(String commandStr) {
  CommandType current = CommandType.valueOf(commandStr.toUpperCase());
  return current.command;
  }
}
