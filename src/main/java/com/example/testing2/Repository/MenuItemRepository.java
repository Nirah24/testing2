//package com.example.testing2.Repository;
//
//import com.example.testing2.Model.MenuItem;
//import com.example.testing2.Utils.DatabaseConnection;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MenuItemRepository {
//
//    public void save(MenuItem menuItem) {
//        String sql = "INSERT INTO menu_items(name, price, category_id) VALUES(?, ?, ?)";
//
//        try (Connection conn = DatabaseConnection.connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, menuItem.getName());
//            pstmt.setDouble(2, menuItem.getPrice());
//            pstmt.setInt(3, menuItem.getCategoryId());
//            pstmt.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<MenuItem> findAll() {
//        List<MenuItem> menuItems = new ArrayList<>();
//        String sql = "SELECT * FROM menu_items";
//
//        try (Connection conn = DatabaseConnection.connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql);
//             ResultSet rs = pstmt.executeQuery()) {
//
//            while (rs.next()) {
//                MenuItem menuItem = new MenuItem(
//                        rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getDouble("price"),
//                        rs.getInt("category_id")
//                );
//                menuItems.add(menuItem);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return menuItems;
//    }
//
//    public List<MenuItem> findByCategory(int categoryId) {
//        List<MenuItem> menuItems = new ArrayList<>();
//        String sql = "SELECT * FROM menu_items WHERE category_id = ?";
//
//        try (Connection conn = DatabaseConnection.connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setInt(1, categoryId);
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                MenuItem menuItem = new MenuItem(
//                        rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getDouble("price"),
//                        rs.getInt("category_id")
//                );
//                menuItems.add(menuItem);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return menuItems;
//    }
//}





//package com.example.testing2.Repository;
//
//import com.example.testing2.Model.MenuItem;
//import com.example.testing2.Utils.DatabaseConnection;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MenuItemRepository {
//
//    private Connection conn;
//
//    public MenuItemRepository() {
//        conn = DatabaseConnection.getConnection();
//    }
//
//    // CREATE
//    public void save(MenuItem item) {
//        String sql = "INSERT INTO menu_items(name, price, category_id) VALUES(?,?,?)";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, item.getName());
//            stmt.setDouble(2, item.getPrice());
//            stmt.setInt(3, item.getCategoryId());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Fetch all menu items
//    public List<MenuItem> findAll() {
//        List<MenuItem> items = new ArrayList<>();
//        String sql = "SELECT * FROM menu_items";
//        try (Connection conn = DatabaseConnection.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                MenuItem item = new MenuItem(
//                        rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getDouble("price"),
//                        rs.getInt("category_id")
//                );
//                items.add(item);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return items;
//    }
//
//    // READ ALL
//    public List<MenuItem> getAll() {
//        List<MenuItem> list = new ArrayList<>();
//        String sql = "SELECT * FROM menu_items";
//        try (Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                MenuItem m = new MenuItem(
//                        rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getDouble("price"),
//                        rs.getInt("category_id")
//                );
//                list.add(m);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    // READ BY ID
//    public MenuItem getById(int id) {
//        String sql = "SELECT * FROM menu_items WHERE id=?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return new MenuItem(
//                        rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getDouble("price"),
//                        rs.getInt("category_id")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // UPDATE
//    public void update(MenuItem item) {
//        String sql = "UPDATE menu_items SET name=?, price=?, category_id=? WHERE id=?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, item.getName());
//            stmt.setDouble(2, item.getPrice());
//            stmt.setInt(3, item.getCategoryId());
//            stmt.setInt(4, item.getId());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // DELETE
//    public void delete(int id) {
//        String sql = "DELETE FROM menu_items WHERE id=?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}




package com.example.testing2.Repository;

import com.example.testing2.Model.MenuItem;
import com.example.testing2.Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemRepository {

    public void save(MenuItem item) {

        String sql = "INSERT INTO menu_items(name, price, category_id) VALUES(?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.setInt(3, item.getCategoryId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MenuItem> findAll() {

        List<MenuItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_items";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                items.add(
                        new MenuItem(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getInt("category_id")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public void delete(int id) {

        String sql = "DELETE FROM menu_items WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}