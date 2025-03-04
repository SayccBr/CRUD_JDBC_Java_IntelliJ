package BASIC_JDBC;

import DB.DB;
import DB.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deletar_dados {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DB.getConnection();

            stmt = conn.prepareStatement("DELETE FROM department WHERE id = ?");

            stmt.setInt(1, 5);

            int rowsaffected = stmt.executeUpdate();

            System.out.println("DONE! rows affected: " + rowsaffected);

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(stmt);
            DB.closeConnection();
        }
    }
}
