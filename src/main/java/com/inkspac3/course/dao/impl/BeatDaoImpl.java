package com.inkspac3.course.dao.impl;

import com.inkspac3.course.connection.CustomHikariDatasource;
import com.inkspac3.course.dao.BeatDao;
import com.inkspac3.course.exception.DaoException;
import com.inkspac3.course.model.Beat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BeatDaoImpl implements BeatDao {
  private static final String SQL_FIND_AVAILABLE =
          "SELECT id, title, price, audio_path, author " +
                  "FROM beats WHERE is_published = true";

  @Override
  public List<Beat> findAllAvailable() throws DaoException {
    List<Beat> beats = new ArrayList<>();

    try (Connection c = CustomHikariDatasource.getUserConnection();
         PreparedStatement ps = c.prepareStatement(SQL_FIND_AVAILABLE);
         ResultSet rs = ps.executeQuery()) {

      while (rs.next()) {
        beats.add(map(rs));
      }
    } catch (SQLException e) {
      throw new DaoException(e);
    }

    return beats;
  }

  private Beat map(ResultSet rs) throws SQLException {
    Beat beat = new Beat();
    beat.setId(rs.getLong("id"));
    beat.setTitle(rs.getString("title"));
    beat.setPrice(rs.getBigDecimal("price"));
    beat.setAuthor(rs.getString("author"));
    beat.setPath(rs.getString("audio_path"));
    return beat;
  }
}
