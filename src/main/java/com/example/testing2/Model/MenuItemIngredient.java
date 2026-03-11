package com.example.testing2.Model;

public class MenuItemIngredient {

    private int id;
    private int menuItemId;
    private int ingredientId;
    private double quantityRequired;

    // Constructor for new mapping
    public MenuItemIngredient(int menuItemId, int ingredientId, double quantityRequired) {
        this.menuItemId = menuItemId;
        this.ingredientId = ingredientId;
        this.quantityRequired = quantityRequired;
    }

    // Constructor when loading from DB
    public MenuItemIngredient(int id, int menuItemId, int ingredientId, double quantityRequired) {
        this.id = id;
        this.menuItemId = menuItemId;
        this.ingredientId = ingredientId;
        this.quantityRequired = quantityRequired;
    }

    public int getId() {
        return id;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public double getQuantityRequired() {
        return quantityRequired;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setQuantityRequired(double quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    @Override
    public String toString() {
        return "MenuItemIngredient{" +
                "id=" + id +
                ", menuItemId=" + menuItemId +
                ", ingredientId=" + ingredientId +
                ", quantityRequired=" + quantityRequired +
                '}';
    }
}