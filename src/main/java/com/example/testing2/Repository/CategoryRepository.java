//package com.example.testing2.Repository;
//
//import com.example.testing2.Model.Category;
//import com.example.testing2.Utils.DatabaseConnection;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CategoryRepository {
//
//    public void save(Category category) {
//
//        String sql = "INSERT INTO categories(name) VALUES(?)";
//
//        try (Connection conn = DatabaseConnection.connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, category.getName());
//            pstmt.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<Category> findAll() {
//
//        List<Category> categories = new ArrayList<>();
//        String sql = "SELECT * FROM categories";
//
//        try (Connection conn = DatabaseConnection.connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql);
//             ResultSet rs = pstmt.executeQuery()) {
//
//            while (rs.next()) {
//                Category category = new Category(
//                        rs.getInt("id"),
//                        rs.getString("name")
//                );
//                categories.add(category);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return categories;
//    }
//}




//
//package com.example.testing2.Repository;
//
//import com.example.testing2.Model.Category;
//import com.example.testing2.Utils.DatabaseConnection;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CategoryRepository {
//
//    private Connection conn;
//
//    public CategoryRepository() {
//        conn = DatabaseConnection.getConnection();
//    }
//
//    // CREATE
//    public void save(Category category) {
//        String sql = "INSERT INTO categories(name) VALUES(?)";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, category.getName());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Fetch all categories
//    public List<Category> findAll() {
//        List<Category> categories = new ArrayList<>();
//        String sql = "SELECT * FROM categories";
//        try (Connection conn = DatabaseConnection.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                Category category = new Category(rs.getInt("id"), rs.getString("name"));
//                categories.add(category);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return categories;
//    }
//
//    // READ ALL
//    public List<Category> getAll() {
//        List<Category> list = new ArrayList<>();
//        String sql = "SELECT * FROM categories";
//        try (Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                Category c = new Category(
//                        rs.getInt("id"),
//                        rs.getString("name")
//                );
//                list.add(c);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    // READ BY ID
//    public Category getById(int id) {
//        String sql = "SELECT * FROM categories WHERE id=?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return new Category(rs.getInt("id"), rs.getString("name"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // UPDATE
//    public void update(Category category) {
//        String sql = "UPDATE categories SET name=? WHERE id=?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, category.getName());
//            stmt.setInt(2, category.getId());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // DELETE
//    public void delete(int id) {
//        String sql = "DELETE FROM categories WHERE id=?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}





//package com.example.testing2.Repository;
//
//import com.example.testing2.Model.Category;
//import com.example.testing2.Utils.DatabaseConnection;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CategoryRepository {
//
//    public void save(Category category) {
//
//        String sql = "INSERT INTO categories(name) VALUES(?)";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, category.getName());
//            pstmt.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<Category> findAll() {
//
//        List<Category> categories = new ArrayList<>();
//        String sql = "SELECT * FROM categories";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                categories.add(
//                        new Category(
//                                rs.getInt("id"),
//                                rs.getString("name")
//                        )
//                );
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return categories;
//    }
//
//    public void delete(int id) {
//
//        String sql = "DELETE FROM categories WHERE id = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setInt(1, id);
//            pstmt.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}


package com.example.testing2.Repository;

import com.example.testing2.Model.Category;
import com.example.testing2.Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    public void save(Category category) {
        String sql = "INSERT OR IGNORE INTO categories(name) VALUES(?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, category.getName());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public Category findById(int id) {
        String sql = "SELECT * FROM categories WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Category findByName(String name) {
        String sql = "SELECT * FROM categories WHERE name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

//    public void update(Category category) {
//        String sql = "UPDATE categories SET name = ? WHERE id = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, category.getName());
//            pstmt.setInt(2, category.getId());
//            pstmt.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void update(Category category) {
        String sql = "UPDATE categories SET name = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, category.getName());
            pstmt.setInt(2, category.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM categories WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        String sql = "SELECT COUNT(*) FROM categories";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.getInt(1) == 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}