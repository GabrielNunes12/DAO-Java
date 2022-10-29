import DAO.DAOFactory;
import DAO.SellerDAO;
import models.Seller;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    SellerDAO sellerDAO = DAOFactory.createSellerDAO();
    List<Seller> sellers = sellerDAO.findAll();
    for(Object sellerObj : sellers) {
      System.out.println(sellerObj);
    }
  }
}