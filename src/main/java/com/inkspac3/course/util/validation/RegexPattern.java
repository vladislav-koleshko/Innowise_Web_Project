package com.inkspac3.course.util.validation;

public final class RegexPattern {
  public static final String USERNAME = "^[A-Za-z0-9_]{4,20}$";

  public static final String EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

  public static final String PASSWORD =
          "^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

  private RegexPattern() {}
}
