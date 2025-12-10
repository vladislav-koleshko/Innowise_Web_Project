package com.inkspac3.course.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {

  public static String encode(String confirmPassword) {
    return BCrypt.hashpw(confirmPassword, BCrypt.gensalt());
  }

  public static boolean verify(String confirmPassword, String encodedPassword) {
    return BCrypt.checkpw(confirmPassword, encodedPassword);
  }
}