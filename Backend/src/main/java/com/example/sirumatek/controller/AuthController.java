package com.example.sirumatek.controller;

import com.example.sirumatek.model.User;
import com.example.sirumatek.repository.UserRepository;
import com.example.sirumatek.service.UserService;
import com.example.sirumatek.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Agrega esto

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Busca el usuario por email
            User foundUser = userService.findByEmail(loginRequest.getEmail());
            // Verifica si el usuario existe y si las contraseñas coinciden
            if (foundUser != null && foundUser.getContrasena().equals(loginRequest.getPassword())) {
                // Genera el token JWT
                String token = jwtUtil.generateToken(foundUser.getCorreo());
                return ResponseEntity.ok(token);
            }

            // Si las credenciales no coinciden, devuelve una respuesta no autorizada
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    private boolean passwordMatches(String rawPassword, String storedPassword) {
        // Implementa la lógica para comparar contraseñas (por ejemplo, utilizando BCrypt)
        return new BCryptPasswordEncoder().matches(rawPassword, storedPassword);
    }
}