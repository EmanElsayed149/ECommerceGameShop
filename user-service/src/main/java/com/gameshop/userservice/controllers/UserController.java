package com.gameshop.userservice.controllers;

import com.gameshop.userservice.models.User;
import com.gameshop.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private Environment env;

    @GetMapping("/test")
    public String test() {
        // Returns which port handled the request
        return "Handled by port: " + env.getProperty("local.server.port");
    }

    @GetMapping
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        return userService.login(username, password);
    }
}
