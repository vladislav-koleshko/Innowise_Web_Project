package com.inkspac3.course.dao;

import com.inkspac3.course.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {
  Optional<T> findById(final long id) throws DaoException;
  List<T> findAll() throws DaoException;
  T save(final T t) throws DaoException;
  boolean delete(final long id) throws DaoException;
  boolean update(final T t) throws DaoException;
}
