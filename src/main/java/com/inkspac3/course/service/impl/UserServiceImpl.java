package com.inkspac3.course.service.impl;

import com.inkspac3.course.service.UserService;

public class UserServiceImpl implements UserService {
  private static final UserService instance = new UserServiceImpl();

  private UserServiceImpl() {}

  public static UserService getInstance() {
    return instance;
  }

  @Override
  public boolean authenticate(String username, String password) {
    return username.equals(password);
  }

  @Override
  public boolean register(String username, String email, String password, String password2) {

    return true;
  }

}
