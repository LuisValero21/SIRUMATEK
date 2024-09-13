package com.example.sirumatek.repository;

import com.example.sirumatek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByCorreo(String correo);

    Optional<User> findByCorreoAndContrasena(String correo, String contrasena);

    @Query(value = "SELECT * FROM usuario", nativeQuery = true)
    List<User> findAllEmployees();
}