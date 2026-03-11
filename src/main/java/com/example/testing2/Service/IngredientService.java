package com.example.testing2.Service;

import com.example.testing2.Model.Ingredient;
import com.example.testing2.Repository.IngredientRepository;

import java.util.List;

public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService() {
        this.ingredientRepository = new IngredientRepository();
    }

    public void addIngredient(String name, double stockQuantity, String unit, double lowStockThreshold) {
        Ingredient ingredient = new Ingredient(name, stockQuantity, unit, lowStockThreshold);
        ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(int id) {
        return ingredientRepository.findById(id);
    }

    public Ingredient getIngredientByName(String name) {
        return ingredientRepository.findByName(name);
    }

    public void updateIngredient(int id, String name, double stockQuantity, String unit, double lowStockThreshold) {
        Ingredient ingredient = new Ingredient(id, name, stockQuantity, unit, lowStockThreshold);
        ingredientRepository.update(ingredient);
    }

    public void deleteIngredient(int id) {
        ingredientRepository.delete(id);
    }

    public List<Ingredient> getLowStockIngredients() {
        return ingredientRepository.findLowStockIngredients();
    }

    public void addStock(int ingredientId, double amount) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId);

        if (ingredient != null) {
            ingredient.setStockQuantity(ingredient.getStockQuantity() + amount);
            ingredientRepository.update(ingredient);
        }
    }

//    public void deductStock(int ingredientId, double amount) {
//        Ingredient ingredient = ingredientRepository.findById(ingredientId);
//
//        if (ingredient != null) {
//            ingredient.setStockQuantity(ingredient.getStockQuantity() - amount);
//            ingredientRepository.update(ingredient);
//        }
//    }

    public void deductStock(int ingredientId, double amount) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId);

        if (ingredient != null && ingredient.getStockQuantity() >= amount) {
            ingredient.setStockQuantity(ingredient.getStockQuantity() - amount);
            ingredientRepository.update(ingredient);
        }
    }
}