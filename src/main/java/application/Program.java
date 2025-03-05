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

        /*
        System.out.println("\n=== TEST 3: seller findAll ===");
         List<Seller> list2 = sellerDao.findAll();
         for (Seller s : list2) {
            System.out.println(s);
        }
        */

        System.out.println("\n=== TEST 4: seller insert ===");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, seller.getDepartment());
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());

        System.out.println("\n=== TEST 5: seller update ===");
        seller = sellerDao.findById(2);
        seller.setName("Saymon");
        sellerDao.update(seller);
        System.out.println("Updated!");
    }
}
