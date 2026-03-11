//package com.example.testing2.Service;
//
//import com.example.testing2.Model.*;
//import com.example.testing2.Repository.*;
//import java.util.List;
//
//public class CheckoutService {
//
//    private final OrderRepository orderRepository;
//    private final OrderItemRepository orderItemRepository;
//    private final MenuItemRepository menuItemRepository;
//    private final MenuItemIngredientRepository menuItemIngredientRepository;
//    private final IngredientRepository ingredientRepository;
//
//    public CheckoutService() {
//        this.orderRepository = new OrderRepository();
//        this.orderItemRepository = new OrderItemRepository();
//        this.menuItemRepository = new MenuItemRepository();
//        this.menuItemIngredientRepository = new MenuItemIngredientRepository();
//        this.ingredientRepository = new IngredientRepository();
//    }
//
//    // Process the checkout
//    public String checkoutOrder(List<CartItem> cartItems, int userId, String paymentMethod) {
//        double totalAmount = 0;
//
//        // 1. Check if there's enough stock for each ingredient in the cart
//        for (CartItem cartItem : cartItems) {
//            MenuItem menuItem = menuItemRepository.findById(cartItem.getMenuItem().getId());
//            if (menuItem == null) {
//                return "Item not found: " + cartItem.getMenuItem();
//            }
//
//            // Retrieve ingredient mappings for this menu item
//            List<MenuItemIngredient> ingredientMappings = menuItemIngredientRepository.findByMenuItemId(menuItem.getId());
//
//            // For each mapping, check if there's enough stock
//            for (MenuItemIngredient mapping : ingredientMappings) {
//                Ingredient ingredient = ingredientRepository.findById(mapping.getIngredientId());
//                if (ingredient != null && ingredient.getStockQuantity() < mapping.getQuantityRequired() * cartItem.getQuantity()) {
//                    return "Insufficient stock for ingredient: " + ingredient.getName();
//                }
//            }
//
//            // Calculate the total for this item
//            totalAmount += menuItem.getPrice() * cartItem.getQuantity();
//        }
//
//        // 2. Create the order
//        String orderCreatedAt = java.time.LocalDateTime.now().toString();
//        Order order = new Order(userId, totalAmount, paymentMethod, orderCreatedAt);
//        int orderId = orderRepository.save(order);
//
//        if (orderId == -1) {
//            return "Failed to create the order.";
//        }
//
//        // 3. Save order items
//        for (CartItem cartItem : cartItems) {
//            MenuItem menuItem = menuItemRepository.findById(cartItem.getMenuItem().getId());
//            if (menuItem != null) {
//                OrderItem orderItem = new OrderItem(orderId, menuItem.getId(), cartItem.getQuantity(), menuItem.getPrice());
//                orderItemRepository.save(orderItem);
//            }
//        }
//
//        // 4. Deduct stock based on ingredients
//        for (CartItem cartItem : cartItems) {
//            MenuItem menuItem = menuItemRepository.findById(cartItem.getMenuItem().getId());
//            if (menuItem != null) {
//                List<MenuItemIngredient> ingredientMappings = menuItemIngredientRepository.findByMenuItemId(menuItem.getId());
//
//                for (MenuItemIngredient mapping : ingredientMappings) {
//                    Ingredient ingredient = ingredientRepository.findById(mapping.getIngredientId());
//                    if (ingredient != null) {
//                        double requiredQuantity = mapping.getQuantityRequired() * cartItem.getQuantity();
//                        ingredient.setStockQuantity(ingredient.getStockQuantity() - requiredQuantity);
//                        ingredientRepository.update(ingredient);
//                    }
//                }
//            }
//        }
//
//        // 5. Return success message
//        return "Order placed successfully! Order ID: " + orderId;
//    }
//}

package com.example.testing2.Service;

import com.example.testing2.Model.*;
import com.example.testing2.Repository.*;

import java.util.List;

public class CheckoutService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MenuItemRepository menuItemRepository;
    private final MenuItemIngredientRepository menuItemIngredientRepository;
    private final IngredientRepository ingredientRepository;

    public CheckoutService() {
        this.orderRepository = new OrderRepository();
        this.orderItemRepository = new OrderItemRepository();
        this.menuItemRepository = new MenuItemRepository();
        this.menuItemIngredientRepository = new MenuItemIngredientRepository();
        this.ingredientRepository = new IngredientRepository();
    }

    // Process the checkout
    public String checkoutOrder(List<CartItem> cartItems, int userId, String paymentMethod) {
        double totalAmount = 0;

        // 1. Check if there's enough stock for each ingredient in the cart
        for (CartItem cartItem : cartItems) {
//            MenuItem menuItem = menuItemRepository.findById(cartItem.getMenuItemId());
            MenuItem menuItem = menuItemRepository.findById(cartItem.getMenuItem().getId());
            if (menuItem == null) {
                return "Item not found: " + cartItem.getMenuItem();
            }

            // Retrieve ingredient mappings for this menu item
            List<MenuItemIngredient> ingredientMappings = menuItemIngredientRepository.findByMenuItemId(menuItem.getId());

            // For each mapping, check if there's enough stock
            for (MenuItemIngredient mapping : ingredientMappings) {
                Ingredient ingredient = ingredientRepository.findById(mapping.getIngredientId());
                if (ingredient != null && ingredient.getStockQuantity() < mapping.getQuantityRequired() * cartItem.getQuantity()) {
                    return "Insufficient stock for ingredient: " + ingredient.getName();
                }
            }

            // Calculate the total for this item
            totalAmount += menuItem.getPrice() * cartItem.getQuantity();
        }

        // 2. Create the order
        String orderCreatedAt = java.time.LocalDateTime.now().toString();
        Order order = new Order(userId, totalAmount, paymentMethod, orderCreatedAt);
        int orderId = orderRepository.save(order);

        if (orderId == -1) {
            return "Failed to create the order.";
        }

        // 3. Save order items
        for (CartItem cartItem : cartItems) {
          //  MenuItem menuItem = menuItemRepository.findById(cartItem.getMenuItemId());
            MenuItem menuItem = menuItemRepository.findById(cartItem.getMenuItem().getId());
            if (menuItem != null) {
                OrderItem orderItem = new OrderItem(orderId, menuItem.getId(), cartItem.getQuantity(), menuItem.getPrice());
                orderItemRepository.save(orderItem);
            }
        }

        // 4. Deduct stock based on ingredients
        for (CartItem cartItem : cartItems) {
        //    MenuItem menuItem = menuItemRepository.findById(cartItem.getMenuItemId());
            MenuItem menuItem = menuItemRepository.findById(cartItem.getMenuItem().getId());
            if (menuItem != null) {
                List<MenuItemIngredient> ingredientMappings = menuItemIngredientRepository.findByMenuItemId(menuItem.getId());

                for (MenuItemIngredient mapping : ingredientMappings) {
                    Ingredient ingredient = ingredientRepository.findById(mapping.getIngredientId());
                    if (ingredient != null) {
                        // Calculate the total quantity of the ingredient required based on cart quantity
                        double requiredQuantity = mapping.getQuantityRequired() * cartItem.getQuantity();
                        double newStock = ingredient.getStockQuantity() - requiredQuantity;

                        // Ensure stock doesn't go negative
                        if (newStock < 0) {
                            return "Not enough stock for " + ingredient.getName();
                        }

                        // Update ingredient stock in the database
                        ingredient.setStockQuantity(newStock);
                        ingredientRepository.update(ingredient);

                        // Verify the stock after deduction (printing for debugging)
                        System.out.println("Updated stock for " + ingredient.getName() + ": " + newStock);
                    }
                }
            }
        }

        // 5. Return success message
        return "Order placed successfully! Order ID: " + orderId;
    }
}