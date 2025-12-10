package com.inkspac3.course.dao;

import com.inkspac3.course.exception.DaoException;
import com.inkspac3.course.model.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User>{
  Optional<User> findById(final long id) throws DaoException;
  Optional<User> findByUsername(final String username) throws DaoException;
  Optional<User> findByEmail(final String email) throws DaoException;
  User save(User user) throws DaoException;
  boolean update(User user) throws DaoException;
  boolean delete(final long id) throws DaoException;
}
