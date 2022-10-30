package DAO.Impl;

import DAO.DepartmentDAO;
import db.DB;
import db.DbException;
import models.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOJDBC implements DepartmentDAO {
  private Connection connection;
  private static PreparedStatement statement = null;
  private static ResultSet resultSet = null;
  public DepartmentDAOJDBC(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void insertDepartment(Department object) {
    try {
      statement = connection.prepareStatement("INSERT INTO department VALUES (?,?)");
      statement.setInt(1,object.getId());
      statement.setString(2, object.getName());
      statement.executeQuery();
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeConnection();
      DB.closeStatement(statement);
    }
  }

  @Override
  public void update(Department obj) {
    try {
      statement = connection.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
      statement.setString(1, obj.getName());
      statement.setInt(2, obj.getId());
      statement.executeQuery();
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeConnection();
      DB.closeStatement(statement);
    }
  }

  @Override
  public void deleteById(Integer id) {
    try {
      statement = connection.prepareStatement("DELETE FROM department WHERE Id = ?");
      statement.setInt(1, id);
      statement.executeQuery();
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeConnection();
      DB.closeStatement(statement);
    }
  }

  @Override
  public Department findById(Integer id) {
    try {
      statement = connection.prepareStatement("SELECT * FROM department WHERE id = ?");
      statement.setInt(1, id);
      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        Department department = implementDepartment(resultSet);
        return department;
      }
      return null;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeConnection();
      DB.closeStatement(statement);
    }
  }

  @Override
  public List<Department> findAll() {
    List<Department> departments = new ArrayList<>();
    try {
      statement = connection.prepareStatement("SELECT * FROM department");
      resultSet = statement.executeQuery();
      while(resultSet.next()) {
        departments.add(implementDepartment(resultSet));
      }
      return departments;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeConnection();
      DB.closeStatement(statement);
      DB.closeResultSet(resultSet);
    }
  }

  private Department implementDepartment(ResultSet resultSet) throws SQLException {
    Department department = new Department();
    department.setId(resultSet.getInt("Id"));
    department.setName(resultSet.getString("Name"));
    return department;
  }
}
