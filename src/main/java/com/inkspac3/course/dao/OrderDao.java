package com.inkspac3.course.dao;

import com.inkspac3.course.exception.DaoException;
import com.inkspac3.course.model.Order;

import java.util.List;

public interface OrderDao {
  Order save(Order order) throws DaoException;
  List<Order> findByUserId(long userId) throws DaoException;
}
