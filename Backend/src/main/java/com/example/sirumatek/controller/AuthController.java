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
            User foundUser = userService.findByEmail(loginRequest.getEmail());

            if (foundUser != null && foundUser.getContrasena().equals(loginRequest.getPassword())) {
                String token = jwtUtil.generateToken(foundUser.getCorreo(), foundUser.getId());
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("user", foundUser.getInfoUser());
                response.put("message", "Inicio de sesion exitoso");
                return ResponseEntity.ok(response);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Encripta la contraseña antes de guardarla
        user.setContrasena(passwordEncoder.encode(user.getContrasena()));
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    private boolean passwordMatches(String rawPassword, String storedPassword) {
        // Implementa la lógica para comparar contraseñas (por ejemplo, utilizando BCrypt)
        return new BCryptPasswordEncoder().matches(rawPassword, storedPassword);
    }
}