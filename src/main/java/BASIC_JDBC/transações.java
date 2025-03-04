package BASIC_JDBC;

import DB.DB;
import DB.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class transações {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = DB.getConnection();

            conn.setAutoCommit(false);

            stmt = conn.createStatement();

            int rows1 = stmt.executeUpdate("UPDATE seller SET basesalary = 2090 WHERE departmentId = 1");

            //Joga um erro falso para testar o rollback, tudo ou nada.
            //int x = 1;
            //if (x < 2){
            // throw new SQLException("Faker error");
            //}

            int rows2 = stmt.executeUpdate("UPDATE seller SET basesalary = 3090 WHERE departmentId = 2");

            conn.commit();

            System.out.println("rows 1 :" + rows1);
            System.out.println("rows 2 :" + rows2);
        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("TRANSACTION ROLLBACK! Caused by: " + e.getMessage());
            } catch (SQLException ex) {
                throw new DbException("Error trying to rollback! Caused by:" + ex.getMessage());
            }
        }
        finally {
            DB.closeStatement(stmt);
            DB.closeConnection();
        }
    }
}
