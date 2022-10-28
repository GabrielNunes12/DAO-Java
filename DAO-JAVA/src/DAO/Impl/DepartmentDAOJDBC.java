package DAO.Impl;

import DAO.DepartmentDAO;
import models.Department;

import java.sql.Connection;
import java.util.List;

public class DepartmentDAOJDBC implements DepartmentDAO {
  private Connection connection;
  public DepartmentDAOJDBC(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void insertDepartment(Department object) {

  }

  @Override
  public void update(Department obj) {

  }

  @Override
  public void deleteById(Integer id) {

  }

  @Override
  public Department findById(Integer id) {
    return null;
  }

  @Override
  public List<Department> findAll() {
    return null;
  }
}
