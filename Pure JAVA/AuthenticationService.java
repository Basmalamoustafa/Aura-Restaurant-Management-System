package com.restaurant;

public class AuthenticationService {
    private UserService userService;

    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public User authenticate(String username, String password) {
        User user = userService.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}