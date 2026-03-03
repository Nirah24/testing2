//package com.example.testing2.Repository;
//
//import com.example.testing2.Model.User;
//import com.example.testing2.Utils.DatabaseConnection;
//
//import java.sql.*;
//
//public class UserRepository {
//
//    // Save new user into database
//    public void save(User user) {
//
//        String sql = "INSERT INTO users(username, password, role) VALUES(?, ?, ?)";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, user.getUsername());
//            pstmt.setString(2, user.getPassword());
//            pstmt.setString(3, user.getRole());
//
//            pstmt.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Create default admin if it doesn't exist
//    public void createDefaultAdmin() {
//        String sql = "INSERT OR IGNORE INTO users (username, password, role) VALUES ('admin', 'admin', 'ADMIN')";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.executeUpdate();
//            System.out.println("Default admin user created.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Find user by username (used for login)
//    public User findByUsername(String username) {
//
//        String sql = "SELECT * FROM users WHERE username = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, username);
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                return new User(
//                        rs.getInt("id"),
//                        rs.getString("username"),
//                        rs.getString("password"),
//                        rs.getString("role")
//                );
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null; // if user not found
//    }
//}




package com.example.testing2.Repository;

import com.example.testing2.Model.User;
import com.example.testing2.Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public void createDefaultAdmin() {

        String sql = """
            INSERT OR IGNORE INTO users (username, password, role)
            VALUES ('admin', 'admin', 'ADMIN')
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.executeUpdate();
            System.out.println("Default admin checked/created.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findByUsername(String username) {

        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // user not found
    }
}