package com.example.sirumatek.repository;

import com.example.sirumatek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByCorreo(String correo); // Asegúrate de que el nombre del método y el campo coincidan

    Optional<com.example.sirumatek.model.User> findByCorreoAndContraseña(String correo, String contraseña);
}