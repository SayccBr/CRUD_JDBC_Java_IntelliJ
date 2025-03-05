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
        Seller seller = sellerDao.findById(2);

        System.out.println(seller);

        System.out.println("\n=== TEST 2: seller findByDepartment ===");
        List<Seller> list = sellerDao.findByDepartment(seller.getDepartment());
        for (Seller s : list) {
            System.out.println(s);
        }

        System.out.println("\n=== TEST 3: seller findAll ===");
        List<Seller> list2 = sellerDao.findAll();
        for (Seller s : list2) {
            System.out.println(s);
        }

    }
}
