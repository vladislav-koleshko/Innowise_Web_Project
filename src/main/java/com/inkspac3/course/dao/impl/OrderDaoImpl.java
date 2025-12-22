package com.inkspac3.course.dao.impl;

import com.inkspac3.course.connection.CustomHikariDatasource;
import com.inkspac3.course.dao.OrderDao;
import com.inkspac3.course.exception.DaoException;
import com.inkspac3.course.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
  private static final String SQL_SET = "SET app.current_user_id = ";
  private static final String SQL_INSERT = """
        INSERT INTO orders (user_id, beat_id, price)
        VALUES (?, ?, ?)
        """;
  private static final String SQL_FIND_BY_USER = """
        SELECT o.id, o.price, o.created_at,
               b.title AS beat_title
        FROM orders o
        JOIN beats b ON b.id = o.beat_id
        WHERE o.user_id = ?
        ORDER BY o.created_at DESC
        """;

  @Override
  public Order save(Order order) throws DaoException {
    try (Connection con = CustomHikariDatasource.getUserConnection()) {
      try (Statement stmt = con.createStatement()) {
        stmt.execute("SET app.current_user_id = " + order.getUserId());
      }

      PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
      ps.setLong(1, order.getUserId());
      ps.setLong(2, order.getBeatId());
      ps.setBigDecimal(3, order.getPrice());

      ps.executeUpdate();

      try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
          order.setId(rs.getLong(1));
        }
      }
      ps.close();
      return order;

    } catch (SQLException e) {
      throw new DaoException("Failed to save order", e);
    }
  }

  @Override
  public List<Order> findByUserId(long userId) throws DaoException {
    List<Order> orders = new ArrayList<>();

    try (Connection con = CustomHikariDatasource.getUserConnection()) {

      try (Statement stmt = con.createStatement()) {
        stmt.execute(SQL_SET + userId);
      }

      try (PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_USER)) {
        ps.setLong(1, userId);
        try (ResultSet rs = ps.executeQuery()) {
          while (rs.next()) {
            orders.add(map(rs));
          }
        }
      }
      return orders;
    } catch (SQLException e) {
      throw new DaoException("Failed to load orders", e);
    }
  }


  private Order map(ResultSet rs) throws SQLException {
    Order o = new Order();
    o.setId(rs.getLong("id"));
    o.setPrice(rs.getBigDecimal("price"));
    o.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
    o.setBeatTitle(rs.getString("beat_title"));
    return o;
  }
}

