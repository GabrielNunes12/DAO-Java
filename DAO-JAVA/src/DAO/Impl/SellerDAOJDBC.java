package DAO.Impl;

import DAO.SellerDAO;
import db.DB;
import db.DbException;
import models.Department;
import models.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellerDAOJDBC implements SellerDAO {
  private Connection connection;
  private static PreparedStatement statement = null;
  private static ResultSet resultSet = null;

  public SellerDAOJDBC(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void insert(Seller obj) {
    try {
      statement = connection.prepareStatement("INSERT INTO seller VALUES (?,?,?,?,?,?)");
      statement.setInt(1, obj.getId());
      statement.setString(2, obj.getName());
      statement.setString(3, obj.getEmail());
      statement.setDate(4, (Date) obj.getBirthDate());
      statement.setDouble(5, obj.getSalary());
      statement.setInt(6, obj.getDepartment().getId());
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeConnection();
      DB.closeStatement(statement);
      DB.closeResultSet(resultSet);
    }
  }

  @Override
  public void update(Seller obj) {
    try {
      statement = connection.prepareStatement("UPDATE seller SET id = "
              + obj.getId() + ", Name =" + obj.getName() + ", Email = "
              + obj.getEmail() + ", BirthDate = " + obj.getBirthDate()
              + ", BaseSalary = " + obj.getSalary() + ", DepartmentId = " + obj.getDepartment().getId() +
              " WHERE id = ?");
      statement.setInt(1, obj.getId());
      statement.executeQuery();
    }catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeConnection();
      DB.closeResultSet(resultSet);
      DB.closeStatement(statement);
    }
  }

  @Override
  public void deleteById(Integer id) {
    try {
      statement = connection.prepareStatement("DELETE FROM seller WHERE Id = ?");
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
  public Seller findById(Integer id) {
    try {
      statement = connection.prepareStatement("SELECT seller.*, department.Name as DepName " +
              "FROM seller INNER JOIN department " +
              "ON seller.DepartmentId = department.Id WHERE seller.Id = ?;");
      statement.setInt(1, id);
      resultSet = statement.executeQuery();
      if(resultSet.next()) {
        Department department = instantiateDepartment(resultSet);
        Seller seller = instantiateSeller(resultSet,department);
        return seller;
      }
      return null;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeConnection();
      DB.closeStatement(statement);
      DB.closeResultSet(resultSet);
    }
  }
  private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
    Seller seller = new Seller();
    seller.setId(resultSet.getInt("Id"));
    seller.setName(resultSet.getString("Name"));
    seller.setEmail(resultSet.getString("Email"));
    seller.setSalary(resultSet.getDouble("BaseSalary"));
    seller.setBirthDate(resultSet.getDate("BirthDate"));
    seller.setDepartment(department);
    return seller;
  }

  private Department instantiateDepartment (ResultSet resultSet) throws SQLException {
    Department department = new Department();
    department.setId(resultSet.getInt("DepartmentId"));
    department.setName(resultSet.getString("DepName"));
    return department;
  }

  private Department instantiateDepartmentId (ResultSet resultSet) throws SQLException {
    Department department = new Department();
    department.setId(resultSet.getInt("DepartmentId"));
    return department;
  }

  @Override
  public List<Seller> findAll() {
    List<Seller> sellers = new ArrayList<>();
    try {
      statement = connection.prepareStatement("SELECT * FROM seller");
      resultSet = statement.executeQuery();
      while(resultSet.next()) {
        Department department = instantiateDepartmentId(resultSet);
        Seller seller = instantiateSeller(resultSet, department);
        sellers.add(seller);
      }
      return sellers;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeConnection();
      DB.closeStatement(statement);
      DB.closeResultSet(resultSet);
    }
  }
}
