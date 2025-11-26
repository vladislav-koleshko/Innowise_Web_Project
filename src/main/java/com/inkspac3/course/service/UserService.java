package com.inkspac3.course.service;

public interface UserService {
   boolean authenticate(String username, String password);
   boolean register(String username, String email, String password, String password2);
}
