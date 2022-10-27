package DAO;

import models.Department;

import java.util.List;

public interface DepartmentDAO {
  void insertDepartment(Department object);
  void update(Department obj);
  void deleteById(Integer id);
  Department findById(Integer id);
  List<Department> findAll();
}
