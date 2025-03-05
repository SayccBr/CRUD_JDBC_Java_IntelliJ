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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                Department dep = instatiateDepartment(rs);

                Seller seller = instatiateSeller(rs, dep);

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
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conn.prepareStatement(
                    "SELECT seller.*, department.nome AS DepName\n" +
                            "FROM seller\n" +
                            "INNER JOIN department\n" +
                            "ON seller.departmentId = department.Id\n" +
                            "WHERE seller.departmentId = ?\n" +
                            "ORDER BY seller.nome");

            ps.setInt(1, department.getId());
            rs = ps.executeQuery();

            List<Seller> list = new ArrayList<Seller>();

            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {

                Department dep = map.get(rs.getInt("departmentId"));

                if (dep == null) {
                    dep = instatiateDepartment(rs);
                    map.put(rs.getInt("departmentId"), dep);
                }

                Seller seller = instatiateSeller(rs, dep);

                list.add(seller);
            }
            return list;

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
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conn.prepareStatement(
                    "SELECT seller.*, department.nome AS DepName\n" +
                            "FROM seller\n" +
                            "INNER JOIN department\n" +
                            "ON seller.departmentId = department.Id\n" +
                            "ORDER BY seller.nome");

            rs = ps.executeQuery();

            List<Seller> list = new ArrayList<Seller>();

            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {

                Department dep = map.get(rs.getInt("departmentId"));

                if (dep == null) {
                    dep = instatiateDepartment(rs);
                    map.put(rs.getInt("departmentId"), dep);
                }

                Seller seller = instatiateSeller(rs, dep);

                list.add(seller);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller seller = new Seller();
        seller.setId(rs.getInt("id"));
        seller.setName(rs.getString("nome"));
        seller.setEmail(rs.getString("email"));
        seller.setBaseSalary(rs.getDouble("baseSalary"));
        seller.setBirthDate(rs.getDate("birthdate"));
        seller.setDepartment(dep);

        return seller;
    }

    private Department instatiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new  Department();
        dep.setId(rs.getInt("departmentId"));
        dep.setName(rs.getString("nome"));

        return dep;
    }
}
