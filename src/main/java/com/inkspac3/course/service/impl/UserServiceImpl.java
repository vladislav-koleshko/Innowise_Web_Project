package com.inkspac3.course.service.impl;

import com.inkspac3.course.dao.UserDao;
import com.inkspac3.course.dao.impl.UserDaoImpl;
import com.inkspac3.course.dto.LoginUserDto;
import com.inkspac3.course.dto.RegisterUserDto;
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

      if (userOpt.isEmpty()) {
        throw new ServiceException("Invalid username");
      }

      User user = userOpt.get();

      if(!PasswordEncoder.verify(dto.getPassword(), user.getPasswordHash())) {
        throw new ServiceException("Invalid password");
      }

      return user;
    } catch (DaoException e) {
      throw new ServiceException("Failed to authenticate user", e);
    }
  }

  @Override
  public User register(RegisterUserDto dto) throws ServiceException, DaoException {
    Optional<User> userOpt = userDao.findByUsername(dto.getUsername());
    if (userOpt.isPresent()) {
      throw new ServiceException("Username is already taken");
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

    try {
      return userDao.save(user);
    } catch (DaoException e) {
      throw new ServiceException(e);
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
