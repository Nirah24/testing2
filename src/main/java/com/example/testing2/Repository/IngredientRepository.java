package com.example.testing2.Repository;

import com.example.testing2.Model.Ingredient;
import com.example.testing2.Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientRepository {

    public void save(Ingredient ingredient) {
        String sql = "INSERT OR IGNORE INTO ingredients(name, stock_quantity, unit, low_stock_threshold) VALUES(?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ingredient.getName());
            pstmt.setDouble(2, ingredient.getStockQuantity());
            pstmt.setString(3, ingredient.getUnit());
            pstmt.setDouble(4, ingredient.getLowStockThreshold());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ingredient> findAll() {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT * FROM ingredients";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ingredients.add(new Ingredient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("stock_quantity"),
                        rs.getString("unit"),
                        rs.getDouble("low_stock_threshold")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ingredients;
    }

    public Ingredient findById(int id) {
        String sql = "SELECT * FROM ingredients WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Ingredient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("stock_quantity"),
                        rs.getString("unit"),
                        rs.getDouble("low_stock_threshold")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Ingredient findByName(String name) {
        String sql = "SELECT * FROM ingredients WHERE name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Ingredient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("stock_quantity"),
                        rs.getString("unit"),
                        rs.getDouble("low_stock_threshold")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Ingredient ingredient) {
        String sql = "UPDATE ingredients SET name = ?, stock_quantity = ?, unit = ?, low_stock_threshold = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ingredient.getName());
            pstmt.setDouble(2, ingredient.getStockQuantity());
            pstmt.setString(3, ingredient.getUnit());
            pstmt.setDouble(4, ingredient.getLowStockThreshold());
            pstmt.setInt(5, ingredient.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM ingredients WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        String sql = "SELECT COUNT(*) FROM ingredients";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.getInt(1) == 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public List<Ingredient> findLowStockIngredients() {
        List<Ingredient> lowStockIngredients = new ArrayList<>();
        String sql = "SELECT * FROM ingredients WHERE stock_quantity <= low_stock_threshold";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lowStockIngredients.add(new Ingredient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("stock_quantity"),
                        rs.getString("unit"),
                        rs.getDouble("low_stock_threshold")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lowStockIngredients;
    }
}