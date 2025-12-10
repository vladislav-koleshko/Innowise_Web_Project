package com.inkspac3.course.dao.impl;

import com.inkspac3.course.connection.HikariConnection;
import com.inkspac3.course.dao.UserDao;
import com.inkspac3.course.exception.DaoException;
import com.inkspac3.course.model.User;
import org.apache.logging.log4j.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
  private static final Logger logger = LogManager.getLogger();
  private static final String SQL_FIND_BY_ID = "SELECT id, username, email, role FROM users WHERE id = ?";
  private static final String SQL_FIND_BY_USERNAME = "SELECT id, username, email, password_hash, role FROM users WHERE username = ?";
  private static final String SQL_FIND_ALL = "SELECT id, username, email, role FROM users";
  private static final String SQL_SAVE = "INSERT INTO users (username, email, role, password_hash) VALUES (?, ?, ?::user_role, ?)";
  private static final String SQL_UPDATE = "UPDATE users SET username = ?, email = ?, password_hash = ?, role = ? WHERE id = ?";
  private static final String SQL_DELETE = "DELETE FROM users WHERE id = ?";
  
  @Override
  public Optional<User> findById(long id) throws DaoException {
    try (Connection connection = HikariConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID)) {

      ps.setLong(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return Optional.of(mapToUser(rs));
        }
      }
    } catch (SQLException e) {
      logger.error("Error finding user by id: {}", id, e);
      throw new DaoException("Error finding user by id: " + id, e);
    }

    return Optional.empty();
  }

  @Override
  public Optional<User> findByUsername(String username) throws DaoException {
    try (Connection connection = HikariConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_USERNAME)) {

      ps.setString(1, username);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return Optional.of(mapToUser(rs));
        }
      }

    } catch (SQLException e) {
      logger.error("Error finding user by username: {}", username, e);
      throw new DaoException("Error finding user by username: " + username, e);
    }

    return Optional.empty();
  }


  @Override
  public List<User> findAll() throws DaoException {
    List<User> users = new ArrayList<>();

    try (Connection connection = HikariConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL)) {
         try (ResultSet rs = ps.executeQuery()) {
           while (rs.next()) {
             users.add(mapToUser(rs));
           }
         }
    } catch (SQLException e) {
      logger.error("Error finding users", e);
      throw new DaoException("Error finding users", e);
    }
    return users;
  }

  @Override
  public User save(User user) throws DaoException {
    try(Connection connection = HikariConnection.getConnection();
      PreparedStatement ps = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS)) {
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
      logger.error("Error saving user: {}", user, e);
      throw new DaoException("Error saving user",e);
    }
  }

  @Override
  public boolean update(User user) throws DaoException {
    try(Connection connection = HikariConnection.getConnection();
    PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {
      ps.setString(1, user.getName());
      ps.setString(2, user.getEmail());
      ps.setString(3, user.getPasswordHash());
      ps.setString(4, user.getRole().toString());
      ps.setLong(5, user.getId());

      int rows = ps.executeUpdate();
      return rows > 0;

    } catch (SQLException e) {
      logger.error("Error updating user: {}", user, e);
      throw new DaoException("Error updating user", e);
    }
  }

  @Override
  public boolean delete(long id) throws DaoException {
    try(Connection connection = HikariConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {
     ps.setLong(1, id);

      int rows = ps.executeUpdate();
      return rows > 0;

    } catch (SQLException e) {
      logger.error("Error deleting user: {}", id, e);
      throw new DaoException("Error deleting user", e);
    }
  }

  private static User mapToUser(ResultSet rs) throws SQLException {
    User user = new User();
    user.setId(rs.getLong("id"));
    user.setName(rs.getString("username"));
    user.setEmail(rs.getString("email"));
    user.setPasswordHash(rs.getString("password_hash"));
    user.setRole(User.Role.valueOf(rs.getString("role")));
    return user;
  }
}
