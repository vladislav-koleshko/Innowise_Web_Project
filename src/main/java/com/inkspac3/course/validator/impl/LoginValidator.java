package com.inkspac3.course.validator.impl;

import com.inkspac3.course.model.dto.LoginUserDto;
import com.inkspac3.course.util.validation.RegexPattern;
import com.inkspac3.course.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginValidator implements Validator<LoginUserDto> {
  public static final Logger logger = LogManager.getLogger();

  @Override
  public boolean validate(LoginUserDto dto) {
    if (dto.getUsername() == null || !dto.getUsername().matches(RegexPattern.USERNAME)) {
      logger.warn("Username is not valid");
      return false;
    }

    if (dto.getPassword() == null || !dto.getPassword().matches(RegexPattern.PASSWORD)) {
      logger.warn("Password is not valid");
      return false;
    }

    return true;
  }
}
