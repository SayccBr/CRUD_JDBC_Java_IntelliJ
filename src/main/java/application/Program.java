package application;

import DB.DB;

import java.sql.Connection;

public class Program {

    public static void main(String[] args) {
        Connection con = DB.getConnection();
        DB.closeConnection();
    }
}
