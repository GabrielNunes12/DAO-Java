package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
  private static Connection connection = null;
  //creating a method to connect with database
  public static Connection getConnection() {
    if(connection == null) {
      try {
        Properties props = loadProperties();
        String url = props.getProperty("dbUrl");
        connection = DriverManager.getConnection(url, props);
      } catch (SQLException exception) {
        throw  new DbException(exception.getMessage());
      }
    }
    return connection;
  }

  //loading all properties from db.properties
  private static Properties loadProperties() {
    try(FileInputStream fileInputStream = new FileInputStream("db.properties")) {
      Properties props = new Properties();
      props.load(fileInputStream);
      return props;
    } catch (IOException exception) {
      throw new DbException(exception.getMessage());
    }
  };

  //closing any open connections
  public static void closeConnection() {
    if(connection != null) {
      try {
        connection.close();
      } catch (SQLException exception) {
        throw new DbException(exception.getMessage());
      }
    }
  }

  public static void closeStatement(Statement statement) {
    try {
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void closeResultSet(ResultSet resultSet) {
    try {
      resultSet.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}