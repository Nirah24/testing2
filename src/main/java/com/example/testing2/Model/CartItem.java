//package com.example.testing2.Model;
//
//public class CartItem {
//
//    private int menuItemId;  // Store just the menu item ID
//    private int quantity;    // how many of this item
//
//    // Constructor
//    public CartItem(int menuItemId, int quantity) {
//        this.menuItemId = menuItemId;
//        this.quantity = quantity;
//    }
//
//    // Getter methods
//    public int getMenuItemId() {
//        return menuItemId;
//    }
//
//    public void setMenuItemId(int menuItemId) {
//        this.menuItemId = menuItemId;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    // Calculate total price for this CartItem
//    public double getTotalPrice(MenuItem menuItem) {
//        return menuItem.getPrice() * quantity;
//    }
//
//    @Override
//    public String toString() {
//        return "CartItem{" +
//                "menuItemId=" + menuItemId +
//                ", quantity=" + quantity +
//                '}';
//    }
//}


package com.example.testing2.Model;

public class CartItem {

    private MenuItem menuItem;  // Store MenuItem object
    private int quantity;       // How many of this item

    // Constructor that accepts a MenuItem and quantity
    public CartItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    // Getter methods
    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Calculate total price for this CartItem
    public double getTotalPrice() {
        return menuItem.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return menuItem.getName() + " x " + quantity;
    }
}