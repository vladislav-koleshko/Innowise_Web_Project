package com.inkspac3.course.service.impl;

import com.inkspac3.course.dao.UserDao;
import com.inkspac3.course.dao.impl.UserDaoImpl;
import com.inkspac3.course.dto.UserDto;
import com.inkspac3.course.exception.DaoException;
import com.inkspac3.course.model.User;
import com.inkspac3.course.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class UserServiceImpl implements UserService {
  private static final UserService instance = new UserServiceImpl();
  private UserDao userDao = new UserDaoImpl();

  private UserServiceImpl() {}

  public static UserService getInstance() {
    return instance;
  }

  @Override
  public boolean authenticate(User user, String password) {
    return BCrypt.checkpw(password, user.getPasswordHash());
  }

  @Override
  public User register(UserDto dto, String passwordConfrim) throws DaoException {
    if (!dto.getPassword().equals(passwordConfrim)) {
      throw new DaoException("Passwords do not match");
    }

    String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());

    User user = new User();
    user.setName(dto.getUsername());
    user.setEmail(dto.getEmail());
    user.setPasswordHash(hashed);

    return userDao.save(user);
  }

  @Override
  public Optional<User> getUser(long id) throws DaoException {
    return userDao.findById(id);
  }

  @Override
  public boolean updateUser(User user) throws DaoException {
    return userDao.update(user);
  }

  @Override
  public boolean deleteUser(long id) throws DaoException {
    return userDao.delete(id);
  }
}
