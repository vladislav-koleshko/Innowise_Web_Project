package com.inkspac3.course.service.impl;

import com.inkspac3.course.dao.UserDao;
import com.inkspac3.course.dao.impl.UserDaoImpl;
import com.inkspac3.course.model.dto.LoginUserDto;
import com.inkspac3.course.model.dto.RegisterUserDto;
import com.inkspac3.course.exception.DaoException;
import com.inkspac3.course.exception.ServiceException;
import com.inkspac3.course.model.User;
import com.inkspac3.course.service.UserService;
import com.inkspac3.course.util.PasswordEncoder;

import java.util.Optional;

public class UserServiceImpl implements UserService {
  private final UserDao userDao = new UserDaoImpl();

  @Override
  public User authenticate(LoginUserDto dto) throws ServiceException {
    try {
      Optional<User> userOpt = userDao.findByUsername(dto.getUsername());

      if (userOpt.isEmpty() || !PasswordEncoder.verify(dto.getPassword(), userOpt.get().getPasswordHash())) {
        throw new ServiceException("Invalid login or password");
      }

      return userOpt.get();
    } catch (DaoException e) {
      throw new ServiceException("Failed to authenticate user", e);
    }
  }

  @Override
  public User register(RegisterUserDto dto) throws ServiceException {
    try {
      Optional<User> userByUsername = userDao.findByUsername(dto.getUsername());
      if (userByUsername.isPresent()) {
        throw new ServiceException("Username is already taken");
      }

      Optional<User> userByEmail = userDao.findByEmail(dto.getEmail());
      if (userByEmail.isPresent()) {
        throw new ServiceException("Email is already taken");
      }

      if (!dto.getPassword().equals(dto.getConfirmPassword())) {
        throw new ServiceException("Passwords do not match");
      }

      var hashedPassword = PasswordEncoder.encode(dto.getPassword());

      User user = new User();
      user.setName(dto.getUsername());
      user.setEmail(dto.getEmail());
      user.setPasswordHash(hashedPassword);
      user.setRole(User.Role.CLIENT);

      return userDao.save(user);
    } catch (DaoException e) {
      throw new ServiceException("Failed to register user", e);
    }
  }

  @Override
  public Optional<User> findUser(long id) throws ServiceException {
    try {
      return userDao.findById(id);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public boolean updateUser(User user) throws ServiceException {
    try {
      return userDao.update(user);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public boolean deleteUser(long id) throws ServiceException {
    try {
      return userDao.delete(id);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
  }
}
