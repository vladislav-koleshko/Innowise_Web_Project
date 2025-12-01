package com.inkspac3.course.service;

import com.inkspac3.course.dto.UserDto;
import com.inkspac3.course.exception.DaoException;
import com.inkspac3.course.model.User;

import java.util.Optional;

public interface UserService {
   boolean authenticate(User user, String password);
   User register(UserDto user, String passwordConfirm) throws DaoException;
   Optional<User> getUser(long id) throws DaoException;
   boolean updateUser(User user) throws DaoException;
   boolean deleteUser(long id) throws DaoException;
}
