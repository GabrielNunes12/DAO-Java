package DAO;

import DAO.Impl.DepartmentDAOJDBC;
import DAO.Impl.SellerDAOJDBC;
import db.DB;

public class DAOFactory {
  public static SellerDAO createSellerDAO() {
    return new SellerDAOJDBC(DB.getConnection());
  };
  public static DepartmentDAO createDepartmentDAO() {
    return new DepartmentDAOJDBC(DB.getConnection());
  }
}
