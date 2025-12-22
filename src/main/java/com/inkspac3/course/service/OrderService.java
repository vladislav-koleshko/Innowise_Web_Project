package com.inkspac3.course.service;

import com.inkspac3.course.exception.ServiceException;
import com.inkspac3.course.model.Beat;
import com.inkspac3.course.model.Order;

import java.util.List;

public interface OrderService {
  void createOrder(Long userId, Beat beat) throws ServiceException;
  List<Order> getUserOrders(Long id) throws ServiceException;
}
