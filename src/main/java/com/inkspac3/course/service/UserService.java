package com.inkspac3.course.service;

import com.inkspac3.course.model.dto.LoginUserDto;
import com.inkspac3.course.model.dto.RegisterUserDto;
import com.inkspac3.course.exception.ServiceException;
import com.inkspac3.course.model.User;

import java.util.Optional;

public interface UserService {
   User authenticate(LoginUserDto user) throws ServiceException;
   User register(RegisterUserDto user) throws ServiceException;
   Optional<User> findUser(long id) throws ServiceException;
   boolean updateUser(User user) throws ServiceException;
   boolean deleteUser(long id) throws ServiceException;
}
