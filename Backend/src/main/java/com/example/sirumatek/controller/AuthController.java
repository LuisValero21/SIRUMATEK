package com.example.sirumatek.controller;

import com.example.sirumatek.model.User;
import com.example.sirumatek.repository.UserRepository;
import com.example.sirumatek.service.UserService;
import com.example.sirumatek.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.sirumatek.service.AuthService;
import com.example.sirumatek.model.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User foundUser = userService.findByEmail(user.getUsername());
            if (foundUser != null && passwordMatches(user.getContrasena(), foundUser.getContrasena())) {
                String token = jwtUtil.generateToken(user.getUsername());
                return ResponseEntity.ok(token);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    private boolean passwordMatches(String rawPassword, String storedPassword) {
        // Implementa la lógica para comparar contraseñas (por ejemplo, utilizando BCrypt)
        return rawPassword.equals(storedPassword);
    }
}