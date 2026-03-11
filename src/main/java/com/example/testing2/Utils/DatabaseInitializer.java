//package com.example.testing2.Utils;
//
//import java.sql.*;
//
//public class DatabaseInitializer {
//
//    public static void initialize() {
//        try (Connection conn = DatabaseConnection.connect();
//             Statement stmt = conn.createStatement()) {
//
//            createUsersTable(stmt);
//            createCategoriesTable(stmt);
//            createMenuItemsTable(stmt);
//            createOrdersTable(stmt);
//            createOrderItemsTable(stmt);
//
//            insertDefaultAdmin();
//
//            System.out.println("Database initialized successfully.");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private static void insertDefaultAdmin() {
//
//        String checkSql = "SELECT * FROM users WHERE username = ?";
//        String insertSql = "INSERT INTO users(username, password, role) VALUES(?, ?, ?)";
//
//        try (Connection conn = DatabaseConnection.connect();
//             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
//
//            checkStmt.setString(1, "admin");
//            ResultSet rs = checkStmt.executeQuery();
//
//            // If admin does NOT exist, insert it
//            if (!rs.next()) {
//
//                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
//
//                    insertStmt.setString(1, "admin");
//                    insertStmt.setString(2, "admin123");  // temporary simple password
//                    insertStmt.setString(3, "ADMIN");
//
//                    insertStmt.executeUpdate();
//
//                    System.out.println("Default admin user created.");
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private static void createUsersTable(Statement stmt) throws SQLException {
//        String sql = """
//                CREATE TABLE IF NOT EXISTS users (
//                    id INTEGER PRIMARY KEY AUTOINCREMENT,
//                    username TEXT NOT NULL UNIQUE,
//                    password TEXT NOT NULL,
//                    role TEXT NOT NULL
//                );
//                """;
//        stmt.execute(sql);
//    }
//
//    private static void createCategoriesTable(Statement stmt) throws SQLException {
//        String sql = """
//                CREATE TABLE IF NOT EXISTS categories (
//                    id INTEGER PRIMARY KEY AUTOINCREMENT,
//                    name TEXT NOT NULL
//                );
//                """;
//        stmt.execute(sql);
//    }
//
//    private static void createMenuItemsTable(Statement stmt) throws SQLException {
//        String sql = """
//                CREATE TABLE IF NOT EXISTS menu_items (
//                    id INTEGER PRIMARY KEY AUTOINCREMENT,
//                    name TEXT NOT NULL,
//                    price REAL NOT NULL,
//                    category_id INTEGER,
//                    FOREIGN KEY (category_id) REFERENCES categories(id)
//                );
//                """;
//        stmt.execute(sql);
//    }
//
//    private static void createOrdersTable(Statement stmt) throws SQLException {
//        String sql = """
//                CREATE TABLE IF NOT EXISTS orders (
//                    id INTEGER PRIMARY KEY AUTOINCREMENT,
//                    user_id INTEGER,
//                    order_time TEXT NOT NULL,
//                    total_amount REAL NOT NULL,
//                    FOREIGN KEY (user_id) REFERENCES users(id)
//                );
//                """;
//        stmt.execute(sql);
//    }
//
//    private static void createOrderItemsTable(Statement stmt) throws SQLException {
//        String sql = """
//                CREATE TABLE IF NOT EXISTS order_items (
//                    id INTEGER PRIMARY KEY AUTOINCREMENT,
//                    order_id INTEGER,
//                    menu_item_id INTEGER,
//                    quantity INTEGER NOT NULL,
//                    price_at_time REAL NOT NULL,
//                    FOREIGN KEY (order_id) REFERENCES orders(id),
//                    FOREIGN KEY (menu_item_id) REFERENCES menu_items(id)
//                );
//                """;
//        stmt.execute(sql);
//    }
//}

//package com.example.testing2.Utils;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class DatabaseInitializer {
//
//    public static void initialize() {
//        try (Connection conn = DatabaseConnection.getConnection();
//             Statement stmt = conn.createStatement()) {
//
//            // --- USERS TABLE ---
//            stmt.execute("""
//                CREATE TABLE IF NOT EXISTS users (
//                    id INTEGER PRIMARY KEY AUTOINCREMENT,
//                    username TEXT UNIQUE NOT NULL,
//                    password TEXT NOT NULL,
//                    role TEXT NOT NULL
//                )
//            """);
//
//            // --- CATEGORIES TABLE ---
//            stmt.execute("""
//                CREATE TABLE IF NOT EXISTS categories (
//                    id INTEGER PRIMARY KEY AUTOINCREMENT,
//                    name TEXT UNIQUE NOT NULL
//                )
//            """);
//
//            // --- MENU_ITEMS TABLE ---
//            stmt.execute("""
//                CREATE TABLE IF NOT EXISTS menu_items (
//                    id INTEGER PRIMARY KEY AUTOINCREMENT,
//                    name TEXT NOT NULL,
//                    price REAL NOT NULL,
//                    category_id INTEGER NOT NULL,
//                    FOREIGN KEY (category_id) REFERENCES categories(id)
//                )
//            """);
//
//            System.out.println("Tables created successfully.");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // Add default data
//        addDefaultData();
//    }
//
//    private static void addDefaultData() {
//        var userRepo = new com.example.testing2.Repository.UserRepository();
//        var categoryRepo = new com.example.testing2.Repository.CategoryRepository();
//        var menuRepo = new com.example.testing2.Repository.MenuItemRepository();
//
//        //  DEFAULT ADMIN USER
//        userRepo.createDefaultAdmin();
//
//        //  DEFAULT CATEGORIES
//        var coffee = new com.example.testing2.Model.Category("Coffee");
//        var tea = new com.example.testing2.Model.Category("Tea");
//        var dessert = new com.example.testing2.Model.Category("Dessert");
//
//        categoryRepo.save(coffee);
//        categoryRepo.save(tea);
//        categoryRepo.save(dessert);
//
//        // Retrieve their IDs
//        int coffeeId = categoryRepo.findAll().stream().filter(c -> c.getName().equals("Coffee")).findFirst().get().getId();
//        int teaId = categoryRepo.findAll().stream().filter(c -> c.getName().equals("Tea")).findFirst().get().getId();
//        int dessertId = categoryRepo.findAll().stream().filter(c -> c.getName().equals("Dessert")).findFirst().get().getId();
//
//        //  DEFAULT MENU ITEMS
//        menuRepo.save(new com.example.testing2.Model.MenuItem("Espresso", 80.0, coffeeId));
//        menuRepo.save(new com.example.testing2.Model.MenuItem("Cappuccino", 120.0, coffeeId));
//        menuRepo.save(new com.example.testing2.Model.MenuItem("Green Tea", 70.0, teaId));
//        menuRepo.save(new com.example.testing2.Model.MenuItem("Cheesecake", 150.0, dessertId));
//
//        System.out.println("Default data inserted successfully.");
//    }
//}


package com.example.testing2.Utils;

import com.example.testing2.Model.Category;
import com.example.testing2.Model.Ingredient;
import com.example.testing2.Model.MenuItem;
import com.example.testing2.Model.MenuItemIngredient;
import com.example.testing2.Repository.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT UNIQUE NOT NULL,
                    password TEXT NOT NULL,
                    role TEXT NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS categories (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT UNIQUE NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS menu_items (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT UNIQUE NOT NULL,
                    price REAL NOT NULL,
                    category_id INTEGER NOT NULL,
                    FOREIGN KEY (category_id) REFERENCES categories(id)
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS ingredients (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT UNIQUE NOT NULL,
                    stock_quantity REAL NOT NULL,
                    unit TEXT NOT NULL,
                    low_stock_threshold REAL NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS menu_item_ingredients (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    menu_item_id INTEGER NOT NULL,
                    ingredient_id INTEGER NOT NULL,
                    quantity_required REAL NOT NULL,
                    UNIQUE(menu_item_id, ingredient_id),
                    FOREIGN KEY (menu_item_id) REFERENCES menu_items(id),
                    FOREIGN KEY (ingredient_id) REFERENCES ingredients(id)
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS orders (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER NOT NULL,
                    total_amount REAL NOT NULL,
                    payment_method TEXT NOT NULL,
                    created_at TEXT NOT NULL,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                )
            """);

                        stmt.execute("""
                CREATE TABLE IF NOT EXISTS order_items (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    order_id INTEGER NOT NULL,
                    menu_item_id INTEGER NOT NULL,
                    quantity INTEGER NOT NULL,
                    price REAL NOT NULL,
                    FOREIGN KEY (order_id) REFERENCES orders(id),
                    FOREIGN KEY (menu_item_id) REFERENCES menu_items(id)
                )
            """);

            System.out.println("Tables created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        addDefaultData();
    }

    private static void addDefaultData() {
        UserRepository userRepo = new UserRepository();
        CategoryRepository categoryRepo = new CategoryRepository();
        MenuItemRepository menuRepo = new MenuItemRepository();
        IngredientRepository ingredientRepo = new IngredientRepository();
        MenuItemIngredientRepository mappingRepo = new MenuItemIngredientRepository();

        // Seed admin only if users table is empty
        if (userRepo.isEmpty()) {
            userRepo.createDefaultAdmin();
        }

        // Seed categories only if categories table is empty
        if (categoryRepo.isEmpty()) {
            categoryRepo.save(new Category("Coffee"));
            categoryRepo.save(new Category("Tea"));
            categoryRepo.save(new Category("Dessert"));
            System.out.println("Default categories inserted.");
        }

        // Seed menu items only if menu_items table is empty
        if (menuRepo.isEmpty()) {
            Category coffee = categoryRepo.findByName("Coffee");
            Category tea = categoryRepo.findByName("Tea");
            Category dessert = categoryRepo.findByName("Dessert");

            if (coffee != null) {
                menuRepo.save(new MenuItem("Espresso", 80.0, coffee.getId()));
                menuRepo.save(new MenuItem("Cappuccino", 120.0, coffee.getId()));
            }

            if (tea != null) {
                menuRepo.save(new MenuItem("Green Tea", 70.0, tea.getId()));
            }

            if (dessert != null) {
                menuRepo.save(new MenuItem("Cheesecake", 150.0, dessert.getId()));
            }

            System.out.println("Default menu items inserted.");
        }

        if (ingredientRepo.isEmpty()) {
            ingredientRepo.save(new Ingredient("Coffee Beans", 1000, "g", 200));
            ingredientRepo.save(new Ingredient("Milk", 5000, "ml", 1000));
            ingredientRepo.save(new Ingredient("Green Tea Leaves", 500, "g", 100));
            ingredientRepo.save(new Ingredient("Cheesecake Slice", 10, "pcs", 2));
            ingredientRepo.save(new Ingredient("Sugar", 2000, "g", 300));

            System.out.println("Default ingredients inserted.");
        }

        if (mappingRepo.isEmpty()) {
            MenuItem espresso = menuRepo.findByName("Espresso");
            MenuItem cappuccino = menuRepo.findByName("Cappuccino");
            MenuItem greenTea = menuRepo.findByName("Green Tea");
            MenuItem cheesecake = menuRepo.findByName("Cheesecake");

            Ingredient coffeeBeans = ingredientRepo.findByName("Coffee Beans");
            Ingredient milk = ingredientRepo.findByName("Milk");
            Ingredient teaLeaves = ingredientRepo.findByName("Green Tea Leaves");
            Ingredient cheesecakeSlice = ingredientRepo.findByName("Cheesecake Slice");
            Ingredient sugar = ingredientRepo.findByName("Sugar");

            if (espresso != null && coffeeBeans != null) {
                mappingRepo.save(new MenuItemIngredient(espresso.getId(), coffeeBeans.getId(), 18));
            }

            if (cappuccino != null && coffeeBeans != null) {
                mappingRepo.save(new MenuItemIngredient(cappuccino.getId(), coffeeBeans.getId(), 18));
            }
            if (cappuccino != null && milk != null) {
                mappingRepo.save(new MenuItemIngredient(cappuccino.getId(), milk.getId(), 150));
            }
            if (cappuccino != null && sugar != null) {
                mappingRepo.save(new MenuItemIngredient(cappuccino.getId(), sugar.getId(), 5));
            }

            if (greenTea != null && teaLeaves != null) {
                mappingRepo.save(new MenuItemIngredient(greenTea.getId(), teaLeaves.getId(), 10));
            }
            if (greenTea != null && sugar != null) {
                mappingRepo.save(new MenuItemIngredient(greenTea.getId(), sugar.getId(), 5));
            }

            if (cheesecake != null && cheesecakeSlice != null) {
                mappingRepo.save(new MenuItemIngredient(cheesecake.getId(), cheesecakeSlice.getId(), 1));
            }

            System.out.println("Default menu-item ingredient mappings inserted.");
        }
    }
}