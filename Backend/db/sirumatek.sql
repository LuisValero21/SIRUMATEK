-- Crear la base de datos SIRUMATEK
CREATE DATABASE SIRUMATEK;

-- Crear tabla Usuario
CREATE TABLE Usuario (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Correo VARCHAR(100) NOT NULL UNIQUE,
    Contraseña VARCHAR(255) NOT NULL,
    Rol VARCHAR(10) NOT NULL
);

-- Crear tabla Empleado
CREATE TABLE Empleado (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Cedula VARCHAR(20) NOT NULL UNIQUE,
    Nombre VARCHAR(100) NOT NULL,
    Apellido VARCHAR(100) NOT NULL,
    Fecha_Nacimiento DATE NOT NULL,
    Fecha_Ingreso DATE NOT NULL,
    Sexo CHAR(1) NOT NULL,
    Correo VARCHAR(100) NOT NULL UNIQUE,
    Teléfono VARCHAR(20) NOT NULL,
    CONSTRAINT chk_sexo CHECK (Sexo IN ('M', 'F'))
);

-- Crear tabla Departamento
CREATE TABLE Departamento (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL UNIQUE,
    CONSTRAINT chk_departamento CHECK (Nombre = 'RRHH')
);

-- Crear tabla Empleado_Departamento para la relación entre Empleado y Departamento
CREATE TABLE Empleado_Departamento (
    Empleado_ID INT NOT NULL,
    Departamento_ID INT NOT NULL,
    PRIMARY KEY (Empleado_ID, Departamento_ID),
    FOREIGN KEY (Empleado_ID) REFERENCES Empleado(ID) ON DELETE CASCADE,
    FOREIGN KEY (Departamento_ID) REFERENCES Departamento(ID) ON DELETE CASCADE
);
