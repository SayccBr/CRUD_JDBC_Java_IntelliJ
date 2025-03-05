package model.dao.impl;

import DB.DB;
import DB.DbException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDAOJDBC implements SellerDAO {

    private Connection conn;

    public SellerDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Seller findById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conn.prepareStatement(
                    "SELECT seller.*, department.nome AS DepName\n" +
                            "FROM seller\n" +
                            "INNER JOIN department\n" +
                            "    ON seller.departmentId = department.Id\n" +
                            "WHERE seller.Id = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("departmentId"));
                dep.setName(rs.getString("nome"));

                Seller seller = new Seller();
                seller.setId(rs.getInt("id"));
                seller.setName(rs.getString("nome"));
                seller.setEmail(rs.getString("email"));
                seller.setBaseSalary(rs.getDouble("baseSalary"));
                seller.setBirthDate(rs.getDate("birthdate"));
                seller.setDepartment(dep);

                return seller;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}
