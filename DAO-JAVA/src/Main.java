import DAO.DAOFactory;
import DAO.SellerDAO;
import models.Seller;

public class Main {
  public static void main(String[] args) {
    SellerDAO sellerDAO = DAOFactory.createSellerDAO();
    Seller seller = sellerDAO.findById(1);
    System.out.println(seller);
  }
}