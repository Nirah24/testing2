package com.example.testing2.Model;

public class Category {

    private int id;
    private String name;

    // Constructor for new category (before saving)
    public Category(String name) {
        this.name = name;
    }

    // Constructor when loading from DB
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name; // useful for ComboBox later
    }
}