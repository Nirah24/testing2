package com.example.testing2.Model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    // Add an item to the cart
    public void addItem(MenuItem menuItem, int quantity) {
        // Check if the item already exists, if so update quantity
        for (CartItem item : items) {
            if (item.getMenuItem().getId() == menuItem.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // If not, add the new item
        items.add(new CartItem(menuItem, quantity));
    }

    // Remove an item from the cart
    public void removeItem(MenuItem menuItem) {
        items.removeIf(item -> item.getMenuItem().getId() == menuItem.getId());
    }

    // Get total cost of the cart
    public double getTotalAmount() {
        return items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public List<CartItem> getItems() {
        return items;
    }
}