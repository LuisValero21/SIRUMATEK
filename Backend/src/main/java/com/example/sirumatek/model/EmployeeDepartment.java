package com.example.sirumatek.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "empleado_departamento")
public class EmployeeDepartment {
    @Id
    private Long empleado_id;

    @Transient
    private Long departamento_id;

    // Relación con Empleado y Departamento

    // Getters y Setters
}