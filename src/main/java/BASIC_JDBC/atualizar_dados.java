package BASIC_JDBC;

import DB.DB;

import java.sql.*;

public class atualizar_dados {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DB.getConnection();

            stmt = conn.prepareStatement("UPDATE seller "
                    + "SET basesalary = basesalary + ?"
                    + "WHERE "
                    +  "(departmentId = ?)");

            stmt.setDouble(1, 500);
            stmt.setInt(2, 1);

            int rowsaffected = stmt.executeUpdate();

            System.out.println("DONE! rows affected: " + rowsaffected);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DB.closeStatement(stmt);
            DB.closeConnection();
        }
    }
}
