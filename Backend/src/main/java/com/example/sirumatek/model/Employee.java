package com.example.sirumatek.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Empleado")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cedula;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private Date fechaIngreso;
    private String sexo;
    private String correo;
    private String telefono;

    // Getters y Setters
}
