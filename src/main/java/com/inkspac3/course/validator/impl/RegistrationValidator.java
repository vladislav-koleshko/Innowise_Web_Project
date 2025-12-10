package com.inkspac3.course.validator.impl;

import com.inkspac3.course.model.dto.RegisterUserDto;
import com.inkspac3.course.util.validation.RegexPattern;
import com.inkspac3.course.validator.Validator;

import org.apache.logging.log4j.*;

public class RegistrationValidator implements Validator<RegisterUserDto> {
  public static final Logger logger = LogManager.getLogger();

  @Override
  public boolean validate(RegisterUserDto dto) {
    if (dto.getUsername() == null || !dto.getUsername().matches(RegexPattern.USERNAME)) {
      logger.warn("Username is not valid");
      return false;
    }

    if (dto.getEmail() == null || !dto.getEmail().matches(RegexPattern.EMAIL)) {
      logger.warn("Email is not valid");
      return false;
    }

    if (dto.getPassword() == null || !dto.getPassword().matches(RegexPattern.PASSWORD)) {
      logger.warn("Password is not valid");
      return false;
    }
    return true;
  }
}

