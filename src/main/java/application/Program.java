package application;

import DB.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            conn = DB.getConnection();

            stmt = conn.createStatement();

            rs = stmt.executeQuery("select * from department");

            while(rs.next()){
                System.out.println(rs.getInt("id") + ", " + rs.getString("nome"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            DB.closeResultSet(rs);
            DB.closeStatement(stmt);
            DB.closeConnection();
        }

    }
}
