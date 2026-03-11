package com.example.testing2.Repository;

import com.example.testing2.Model.MenuItemIngredient;
import com.example.testing2.Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemIngredientRepository {

    public void save(MenuItemIngredient mapping) {
        String sql = """
            INSERT OR IGNORE INTO menu_item_ingredients(menu_item_id, ingredient_id, quantity_required)
            VALUES(?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, mapping.getMenuItemId());
            pstmt.setInt(2, mapping.getIngredientId());
            pstmt.setDouble(3, mapping.getQuantityRequired());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MenuItemIngredient> findAll() {
        List<MenuItemIngredient> mappings = new ArrayList<>();
        String sql = "SELECT * FROM menu_item_ingredients";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                mappings.add(new MenuItemIngredient(
                        rs.getInt("id"),
                        rs.getInt("menu_item_id"),
                        rs.getInt("ingredient_id"),
                        rs.getDouble("quantity_required")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mappings;
    }

    public MenuItemIngredient findById(int id) {
        String sql = "SELECT * FROM menu_item_ingredients WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new MenuItemIngredient(
                        rs.getInt("id"),
                        rs.getInt("menu_item_id"),
                        rs.getInt("ingredient_id"),
                        rs.getDouble("quantity_required")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<MenuItemIngredient> findByMenuItemId(int menuItemId) {
        List<MenuItemIngredient> mappings = new ArrayList<>();
        String sql = "SELECT * FROM menu_item_ingredients WHERE menu_item_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, menuItemId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                mappings.add(new MenuItemIngredient(
                        rs.getInt("id"),
                        rs.getInt("menu_item_id"),
                        rs.getInt("ingredient_id"),
                        rs.getDouble("quantity_required")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mappings;
    }

    public List<MenuItemIngredient> findByIngredientId(int ingredientId) {
        List<MenuItemIngredient> mappings = new ArrayList<>();
        String sql = "SELECT * FROM menu_item_ingredients WHERE ingredient_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ingredientId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                mappings.add(new MenuItemIngredient(
                        rs.getInt("id"),
                        rs.getInt("menu_item_id"),
                        rs.getInt("ingredient_id"),
                        rs.getDouble("quantity_required")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mappings;
    }

    public void update(MenuItemIngredient mapping) {
        String sql = """
            UPDATE menu_item_ingredients
            SET menu_item_id = ?, ingredient_id = ?, quantity_required = ?
            WHERE id = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, mapping.getMenuItemId());
            pstmt.setInt(2, mapping.getIngredientId());
            pstmt.setDouble(3, mapping.getQuantityRequired());
            pstmt.setInt(4, mapping.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM menu_item_ingredients WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        String sql = "SELECT COUNT(*) FROM menu_item_ingredients";

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