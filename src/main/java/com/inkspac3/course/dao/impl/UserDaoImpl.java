package com.inkspac3.course.dao.impl;

import com.inkspac3.course.connection.CustomHikariDatasource;
import com.inkspac3.course.dao.UserDao;
import com.inkspac3.course.exception.DaoException;
import com.inkspac3.course.model.User;
import org.apache.logging.log4j.*;

import java.sql.*;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
  private static final Logger logger = LogManager.getLogger();
  private static final String SQL_FIND_BY_ID = "SELECT id, username, email, role FROM users WHERE id = ?";
  private static final String SQL_FIND_BY_USERNAME = "SELECT id, username, email, password_hash, role FROM users WHERE username = ?";
  private static final String SQL_FIND_BY_EMAIL = "SELECT id, username, email, password_hash, role FROM users WHERE email = ?";
  private static final String SQL_SAVE = "INSERT INTO users (username, email, role, password_hash) VALUES (?, ?, ?::user_role, ?)";
  private static final String SQL_UPDATE = "UPDATE users SET username = ?, email = ?, password_hash = ?, role = ?::user_role WHERE id = ?";
  private static final String SQL_DELETE = "DELETE FROM users WHERE id = ?";
  private static final String SQL_SET_USER = "SET app.current_user_id = ";

  @Override
  public Optional<User> findById(long id) throws DaoException {
    return findUser(SQL_FIND_BY_ID, id, id);
  }

  @Override
  public Optional<User> findByUsername(String username, long currentUserId) throws DaoException {
    return findUser(SQL_FIND_BY_USERNAME, currentUserId, username);
  }

  @Override
  public Optional<User> findByEmail(String email, long currentUserId) throws DaoException {
    return findUser(SQL_FIND_BY_EMAIL, currentUserId, email);
  }

  @Override
  public User save(User user) throws DaoException {
    try (Connection connection = CustomHikariDatasource.getServiceConnection();
         PreparedStatement ps = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS)) {
      ps.setString(1, user.getName());
      ps.setString(2, user.getEmail());
      ps.setString(3, user.getRole().toString());
      ps.setString(4, user.getPasswordHash());
      ps.executeUpdate();

      try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
          user.setId(rs.getLong(1));
        }
      }
      return user;
    } catch (SQLException e) {
      logger.error("Error saving user: {}", user, e);
      throw new DaoException("Error saving user", e);
    }
  }

  @Override
  public boolean update(User user) throws DaoException {
    try (Connection connection = CustomHikariDatasource.getUserConnection()) {
      setCurrentUserId(connection, user.getId());

      try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPasswordHash());
        ps.setString(4, user.getRole().toString());
        ps.setLong(5, user.getId());

        int rows = ps.executeUpdate();
        return rows > 0;

      }
    } catch (SQLException e) {
      logger.error("Error updating user: {}", user, e);
      throw new DaoException("Error updating user", e);
    }
  }

  @Override
  public boolean delete(long id) throws DaoException {
    try (Connection connection = CustomHikariDatasource.getServiceConnection()) {
      setCurrentUserId(connection, id);

      try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {
        ps.setLong(1, id);

        int rows = ps.executeUpdate();
        return rows > 0;

      }
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

  private void setCurrentUserId(Connection connection, long userId) throws SQLException {
    try (Statement stmt = connection.createStatement()) {
      stmt.execute(SQL_SET_USER + userId);
    }
  }

  private Optional<User> findUser(String sql, long currentUserId, Object... params) throws DaoException {
    try (Connection connection = CustomHikariDatasource.getServiceConnection()) {
      try (Statement stmt = connection.createStatement()) {
        stmt.execute(SQL_SET_USER + currentUserId);
      }

      try (PreparedStatement ps = connection.prepareStatement(sql)) {
        for (int i = 0; i < params.length; i++) {
          ps.setObject(i + 1, params[i]);
        }

        try (ResultSet rs = ps.executeQuery()) {
          return rs.next() ? Optional.of(mapToUser(rs)) : Optional.empty();
        }
      }
    } catch (SQLException e) {
      logger.error("Error executing query: {}", sql, e);
      throw new DaoException("Error executing query: " + sql, e);
    }
  }

}
