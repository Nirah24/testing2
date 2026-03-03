//package com.example.testing2.Utils;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DatabaseConnection {
//
//    private static final String URL = "jdbc:sqlite:cafe.db";
//
//    public static Connection connect() throws SQLException {
//        return DriverManager.getConnection(URL);
//    }
//
//    public static void main(String[] args) {
//        try (Connection conn = DatabaseConnection.connect()) {
//            if (conn != null) {
//                System.out.println("Connected to SQLite successfully!");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}

//package com.example.testing2.Utils;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DatabaseConnection {
//
//    private static final String URL = "jdbc:sqlite:cafe.db"; // your SQLite DB file
//    private static Connection connection;
//
//
//    // private constructor so no one can instantiate this class
//    private DatabaseConnection() {}
//
//    // This method gives you a single shared connection
//    public static Connection getConnection() {
//        if (connection == null) {
//            try {
//                connection = DriverManager.getConnection(URL);
//                System.out.println("Connected to SQLite successfully!");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return connection;
//    }
//}




//package com.example.testing2.Utils;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DatabaseConnection {
//
//    private static final String URL = "jdbc:sqlite:cafe.db";
//    private static Connection connection;
//
//    private DatabaseConnection() {}
//
//    public static Connection getConnection() {
//        if (connection == null) {
//            try {
//                connection = DriverManager.getConnection(URL);
//                System.out.println("Connected to SQLite successfully!");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return connection;
//    }
//
//    // only close at the very end of app
//    public static void closeConnection() {
//        if (connection != null) {
//            try {
//                connection.close();
//                System.out.println("Connection closed.");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}



package com.example.testing2.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:cafe.db";

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}