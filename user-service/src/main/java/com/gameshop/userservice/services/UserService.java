package com.gameshop.userservice.services;

import com.gameshop.userservice.models.User;
import com.gameshop.userservice.repositories.UserRepository;
import com.gameshop.userservice.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
    public String register(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return "❌ Username already exists!";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "✅ User registered successfully!";
    }

    public Map<String, String> login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        Map<String, String> response = new HashMap<>();

        if (user.isEmpty()) {
            response.put("error", "User not found!");
            return response;
        }

        if (passwordEncoder.matches(password, user.get().getPassword())) {
            String token = jwtUtil.generateToken(username,user.get().getRole());
            response.put("message", "Login successful!");
            response.put("token", token);
        } else {
            response.put("error", "Invalid password!");
        }
        return response;
    }
}
