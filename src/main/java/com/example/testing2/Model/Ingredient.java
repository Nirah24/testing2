package com.example.testing2.Model;

public class Ingredient {

    private int id;
    private String name;
    private double stockQuantity;
    private String unit;
    private double lowStockThreshold;

    public Ingredient(String name, double stockQuantity, String unit, double lowStockThreshold) {
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.unit = unit;
        this.lowStockThreshold = lowStockThreshold;
    }

    public Ingredient(int id, String name, double stockQuantity, String unit, double lowStockThreshold) {
        this.id = id;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.unit = unit;
        this.lowStockThreshold = lowStockThreshold;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getStockQuantity() {
        return stockQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public double getLowStockThreshold() {
        return lowStockThreshold;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStockQuantity(double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setLowStockThreshold(double lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }

    public boolean isLowStock() {
        return stockQuantity <= lowStockThreshold;
    }

    @Override
    public String toString() {
        return name + " - " + stockQuantity + " " + unit;
    }
}