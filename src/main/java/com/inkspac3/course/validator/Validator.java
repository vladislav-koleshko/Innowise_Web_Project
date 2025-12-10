package com.inkspac3.course.validator;

public interface Validator<T>{
  boolean validate(T obj);
}
