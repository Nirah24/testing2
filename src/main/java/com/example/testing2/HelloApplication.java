package com.example.testing2;

import com.example.testing2.Model.Category;
import com.example.testing2.Model.MenuItem;
import com.example.testing2.Model.User;
import com.example.testing2.Repository.CategoryRepository;
import com.example.testing2.Repository.MenuItemRepository;
import com.example.testing2.Repository.UserRepository;
import com.example.testing2.Service.UserService;
import com.example.testing2.Utils.DatabaseInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }



    // TESTING
    public static void main(String[] args) {
        DatabaseInitializer.initialize();

    //    UserRepository repo = new UserRepository();

    // Create new user
//        User user = new User("admin", "1234", "ADMIN");
//        repo.save(user);

    // Retrieve user
//        User found = repo.findByUsername("admin");
//
//        if (found != null) {
//            System.out.println(found);
//        }

        UserService userService = new UserService();

        User loggedIn = userService.login("admin", "admin123");

        if (loggedIn != null) {
            System.out.println("Login successful: " + loggedIn.getUsername());
        } else {
            System.out.println("Login failed.");
        }

        CategoryRepository repo = new CategoryRepository();

        repo.save(new Category("Coffee"));
        repo.save(new Category("Tea"));
        repo.save(new Category("Pastries"));

        System.out.println(repo.findAll());

        CategoryRepository categoryRepo = new CategoryRepository();
        MenuItemRepository menuRepo = new MenuItemRepository();

        Category coffee = new Category("Coffee");
        categoryRepo.save(coffee);

// Retrieve categoryId
        int coffeeId = categoryRepo.findAll().get(0).getId();

        menuRepo.save(new MenuItem("Espresso", 80.0, coffeeId));
        menuRepo.save(new MenuItem("Cappuccino", 120.0, coffeeId));

        System.out.println(menuRepo.findAll());
    }



//    public static void main(String[] args) {
//        launch();
//    }
}
