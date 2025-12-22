package com.inkspac3.course.service.impl;

import com.inkspac3.course.dao.OrderDao;
import com.inkspac3.course.dao.impl.OrderDaoImpl;
import com.inkspac3.course.exception.DaoException;
import com.inkspac3.course.exception.ServiceException;
import com.inkspac3.course.model.Beat;
import com.inkspac3.course.model.Order;
import com.inkspac3.course.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void createOrder(Long userId, Beat beat) throws ServiceException {
      try {
        Order order = new Order();
        order.setUserId(userId);
        order.setBeatId(beat.getId());
        order.setPrice(beat.getPrice());

        orderDao.save(order);
      } catch (DaoException e) {
        throw new ServiceException("Failed to create order", e);
      }
    }

    @Override
    public List<Order> getUserOrders(Long userId) throws ServiceException {
      try {
        return orderDao.findByUserId(userId);
      } catch (DaoException e) {
        throw new ServiceException("Failed to load orders", e);
      }
    }
}
