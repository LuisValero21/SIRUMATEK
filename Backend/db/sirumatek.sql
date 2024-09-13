-- Crear la base de datos SIRUMATEK
CREATE DATABASE SIRUMATEK;

-- Crear tabla Usuario
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(10) NOT NULL
);

-- Crear tabla Empleado
CREATE TABLE empleado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cedula VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    fecha_ingreso DATE NOT NULL,
    sexo CHAR(1) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL,
    CONSTRAINT chk_sexo CHECK (sexo IN ('M', 'F'))
);

-- Crear tabla Departamento
CREATE TABLE departamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    CONSTRAINT chk_departamento CHECK (nombre = 'RRHH')
);

-- Crear tabla Empleado_Departamento para la relación entre Empleado y Departamento
CREATE TABLE empleado_departamento (
    empleado_id INT NOT NULL,
    departamento_id INT NOT NULL,
    PRIMARY KEY (empleado_id, departamento_id),
    FOREIGN KEY (empleado_id) REFERENCES empleado(id) ON DELETE CASCADE,
    FOREIGN KEY (departamento_id) REFERENCES departamento(id) ON DELETE CASCADE
);
