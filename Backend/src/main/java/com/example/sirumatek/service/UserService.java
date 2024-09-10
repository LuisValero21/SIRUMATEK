package com.example.sirumatek.service;

import com.example.sirumatek.model.User;
import com.example.sirumatek.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private List<User> allUsers;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public com.example.sirumatek.model.User findByEmail(String email) {
        return userRepository.findByCorreo(email);
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    public User createUser(User user) {
        return user;
    }

    public User getUserById(Long id) { return null; }
}