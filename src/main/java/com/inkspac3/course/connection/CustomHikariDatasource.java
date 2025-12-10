package com.inkspac3.course.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomHikariDatasource {

  private static final HikariDataSource serviceDataSource;
  private static final HikariDataSource userDataSource;
  private static final Dotenv dotenv = Dotenv.load();

  static {
    HikariConfig serviceConfig = new HikariConfig();
    serviceConfig.setJdbcUrl(dotenv.get("DB_URL"));
    serviceConfig.setUsername(dotenv.get("DB_SERVICE_USERNAME"));
    serviceConfig.setPassword(dotenv.get("DB_SERVICE_PASSWORD"));
    serviceConfig.setDriverClassName("org.postgresql.Driver");
    serviceDataSource = new HikariDataSource(serviceConfig);

    HikariConfig userConfig = new HikariConfig();
    userConfig.setJdbcUrl(dotenv.get("DB_URL"));
    userConfig.setUsername(dotenv.get("DB_USERNAME"));
    userConfig.setPassword(dotenv.get("DB_PASSWORD"));
    userConfig.setMaximumPoolSize(Integer.parseInt(dotenv.get("MAXIMUM_POOL_SIZE")));
    userConfig.setMinimumIdle(Integer.parseInt(dotenv.get("MINIMUM_IDLE")));
    userConfig.setIdleTimeout(Long.parseLong(dotenv.get("IDLE_TIMEOUT")));
    userConfig.setConnectionTimeout(Long.parseLong(dotenv.get("CONNECTION_TIMEOUT")));
    userConfig.setPoolName("HikariAudioPool");
    userConfig.setDriverClassName("org.postgresql.Driver");
    serviceConfig.setMaximumPoolSize(5);
    userDataSource = new HikariDataSource(userConfig);
  }

  private CustomHikariDatasource() {}

  public static Connection getServiceConnection() throws SQLException {
    return serviceDataSource.getConnection();
  }

  public static Connection getUserConnection() throws SQLException {
    return userDataSource.getConnection();
  }
}
