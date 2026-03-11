package com.example.testing2;

import com.example.testing2.Model.*;
import com.example.testing2.Repository.*;
import com.example.testing2.Service.CheckoutService;
import com.example.testing2.Service.IngredientService;
import com.example.testing2.Service.UserService;
import com.example.testing2.Utils.DatabaseInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }



//    // TESTING
//    public static void main(String[] args) {
//        DatabaseInitializer.initialize();
//
//    //    UserRepository repo = new UserRepository();
//
//    // Create new user
////        User user = new User("admin", "1234", "ADMIN");
////        repo.save(user);
//
//    // Retrieve user
////        User found = repo.findByUsername("admin");
////
////        if (found != null) {
////            System.out.println(found);
////        }
//
//        UserService userService = new UserService();
//
//        User loggedIn = userService.login("admin", "admin123");
//
//        if (loggedIn != null) {
//            System.out.println("Login successful: " + loggedIn.getUsername());
//        } else {
//            System.out.println("Login failed.");
//        }
//
//        CategoryRepository repo = new CategoryRepository();
//
//        repo.save(new Category("Coffee"));
//        repo.save(new Category("Tea"));
//        repo.save(new Category("Pastries"));
//
//        System.out.println(repo.findAll());
//
//        CategoryRepository categoryRepo = new CategoryRepository();
//        MenuItemRepository menuRepo = new MenuItemRepository();
//
//        Category coffee = new Category("Coffee");
//        categoryRepo.save(coffee);
//
//// Retrieve categoryId
//        int coffeeId = categoryRepo.findAll().get(0).getId();
//
//        menuRepo.save(new MenuItem("Espresso", 80.0, coffeeId));
//        menuRepo.save(new MenuItem("Cappuccino", 120.0, coffeeId));
//
//        System.out.println(menuRepo.findAll());
//    }
//
//
//
////    public static void main(String[] args) {
////        launch();
////    }
//}


    public static void main(String[] args) {
        DatabaseInitializer.initialize();

        UserService userService = new UserService();
        User loggedIn = userService.login("admin", "admin");

        if (loggedIn != null) {
            System.out.println("Login successful: " + loggedIn.getUsername() + " (" + loggedIn.getRole() + ")");
        } else {
            System.out.println("Login failed.");
        }

        CategoryRepository categoryRepo = new CategoryRepository();
        MenuItemRepository menuRepo = new MenuItemRepository();

        System.out.println("Categories: " + categoryRepo.findAll().size());
        System.out.println("Menu items: " + menuRepo.findAll().size());

        IngredientService ingredientService = new IngredientService();

        System.out.println("Ingredients: " + ingredientService.getAllIngredients().size());
        System.out.println("Ingredient List: " + ingredientService.getAllIngredients());
        System.out.println("Low-stock Ingredients: " + ingredientService.getLowStockIngredients());

        MenuItemIngredientRepository mappingRepo = new MenuItemIngredientRepository();
        System.out.println("Mappings: " + mappingRepo.findAll().size());
        System.out.println("All Mappings: " + mappingRepo.findAll());

        OrderRepository orderRepo = new OrderRepository();
        OrderItemRepository orderItemRepo = new OrderItemRepository();
        UserRepository userRepo = new UserRepository();
    //    MenuItemRepository menuRepo = new MenuItemRepository();

        User admin = userRepo.findByUsername("admin");
        MenuItem espresso = menuRepo.findByName("Espresso");
        MenuItem cappuccino = menuRepo.findByName("Cappuccino");

        if (admin != null && espresso != null && cappuccino != null) {
            Order order = new Order(admin.getId(), 200.0, "CASH", java.time.LocalDateTime.now().toString());
            int orderId = orderRepo.save(order);

            if (orderId != -1) {
                orderItemRepo.save(new OrderItem(orderId, espresso.getId(), 1, espresso.getPrice()));
                orderItemRepo.save(new OrderItem(orderId, cappuccino.getId(), 1, cappuccino.getPrice()));

                System.out.println("Saved Order ID: " + orderId);
                System.out.println("Order: " + orderRepo.findById(orderId));
                System.out.println("Order Items: " + orderItemRepo.findByOrderId(orderId));
            }
        }


        CartItem cartItem1 = new CartItem(new MenuItem(1, "Espresso", 80.0, 1), 2);  // Espresso x 2
        CartItem cartItem2 = new CartItem(new MenuItem(2, "Cappuccino", 120.0, 2), 1);  // Cappuccino x 1

        // Create checkout service and process the checkout
        CheckoutService checkoutService = new CheckoutService();
        String result = checkoutService.checkoutOrder(
                Arrays.asList(cartItem1, cartItem2),
                1,  // userId (admin user)
                "CASH"
        );

        // Output result
        System.out.println(result);  // Expect success message or error if stock is insufficient



        // Later, replace this with launch(args) for JavaFX
    }

}
