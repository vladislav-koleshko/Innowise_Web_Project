package com.inkspac3.course.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnection {
  private static HikariDataSource ds;
  private static Dotenv dotenv = Dotenv.load();

  static {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(dotenv.get("DB_URL"));
    config.setUsername(dotenv.get("DB_USERNAME"));
    config.setPassword(dotenv.get("DB_PASSWORD"));
    config.setMaximumPoolSize(Integer.parseInt(dotenv.get("MAXIMUM_POOL_SIZE")));
    config.setMinimumIdle(Integer.parseInt(dotenv.get("MINIMUM_IDLE")));
    config.setIdleTimeout(Integer.parseInt(dotenv.get("IDLE_TIMEOUT")));
    config.setConnectionTimeout(Integer.parseInt(dotenv.get("CONNECTION_TIMEOUT")));
    config.setPoolName("HikariAudioPool");

    ds = new HikariDataSource(config);
  }

  private HikariConnection() {}

  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
}
