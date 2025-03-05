package application;

import model.dao.DAOFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        SellerDAO sellerDao = DAOFactory.createSellerDAO();

        System.out.println("=== TEST 1: seller findById ===");
        Seller seller = sellerDao.findById(6);

        System.out.println(seller);

        System.out.println("\n=== TEST 2: seller findByDepartment ===");
        List<Seller> sellers = sellerDao.findByDepartment(seller.getDepartment());
        for (Seller s : sellers) {
            System.out.println(s);
        }

    }
}
