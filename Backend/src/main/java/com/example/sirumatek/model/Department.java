package com.example.sirumatek.model;

import javax.persistence.*;

@Entity
@Table(name = "departamento")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    // Getters y Setters
}
