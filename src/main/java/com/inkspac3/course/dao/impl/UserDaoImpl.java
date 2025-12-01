package com.inkspac3.course.dao.impl;

import com.inkspac3.course.connection.HikariConnection;
import com.inkspac3.course.dao.UserDao;
import com.inkspac3.course.exception.DaoException;
import com.inkspac3.course.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
  private static final String FIND_BY_ID = "SELECT id, username, email FROM users WHERE id = ?";
  private static final String FIND_ALL = "SELECT id, username, email, role FROM users";
  private static final String SAVE = "INSERT INTO users (username, email, role, password_hash) VALUES (?, ?, ?, ?)";
  private static final String UPDATE = "UPDATE users SET username = ?, email = ?, role = ? WHERE id = ?";
  private static final String DELETE = "DELETE FROM users WHERE id = ?";
  private HikariConnection connection;
  
  @Override
  public Optional<User> findById(long id) throws DaoException {
    try (Connection connection = HikariConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {

      ps.setLong(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          User user = new User();
          user.setId(rs.getLong("id"));
          user.setName(rs.getString("username"));
          user.setEmail(rs.getString("email"));
          return Optional.of(user);
        }
      }
    } catch (SQLException e) {
      throw new DaoException("Error finding user by id: " + id, e);
    }

    return Optional.empty();
  }

  @Override
  public List<User> findAll() throws DaoException {
    List<User> users = new ArrayList<>();

    try (Connection connection = HikariConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
         try (ResultSet rs = ps.executeQuery()) {
           while (rs.next()) {
             User user = new User();
             user.setId(rs.getLong("id"));
             user.setName(rs.getString("username"));
             user.setEmail(rs.getString("email"));
             user.setRole(User.Role.valueOf(rs.getString("role")));
             users.add(user);
           }
         }
    } catch (SQLException e) {
      throw new DaoException("Error finding users. There are no users in Database!");
    }
    return users;
  }

  @Override
  public User save(User user) throws DaoException {
    try(Connection connection = HikariConnection.getConnection();
      PreparedStatement ps = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
      ps.setString(1, user.getName());
      ps.setString(2, user.getEmail());
      ps.setString(3, user.getRole().toString());
      ps.setString(4, user.getPasswordHash());
      ps.executeUpdate();

      try(ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
          user.setId(rs.getLong(1));
        }
      }
      return user;
    } catch (SQLException e) {
      throw new DaoException("Error saving user!",e);
    }
  }

  @Override
  public boolean update(User user) throws DaoException {
    try(Connection connection = HikariConnection.getConnection();
    PreparedStatement ps = connection.prepareStatement(UPDATE)) {
      ps.setString(1, user.getName());
      ps.setString(2, user.getEmail());
      ps.setString(3, user.getRole().toString());

      int rows = ps.executeUpdate();
      return rows > 0;

    } catch (SQLException e) {
      throw new DaoException("Error updating user!", e);
    }
  }

  @Override
  public boolean delete(long id) throws DaoException {
    try(Connection connection = HikariConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE)) {
     ps.setLong(1, id);

      int rows = ps.executeUpdate();
      return rows > 0;

    } catch (SQLException e) {
      throw new DaoException("Error updating user!", e);
    }
  }
}
