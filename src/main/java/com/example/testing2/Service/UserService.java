package com.example.testing2.Service;

import com.example.testing2.Model.User;
import com.example.testing2.Repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User login(String username, String password) {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null; // user does not exist
        }

        // Simple password check (we will improve this later)
        if (user.getPassword().equals(password)) {
            return user; // login successful
        }

        return null; // wrong password
    }
}