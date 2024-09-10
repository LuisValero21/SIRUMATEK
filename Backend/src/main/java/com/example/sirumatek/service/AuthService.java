package com.example.sirumatek.service;

import com.example.sirumatek.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sirumatek.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String email, String password) {
        User user;
        user = userRepository.findByCorreo(email);
        return user != null && user.getContraseña().equals(password);
    }
}
