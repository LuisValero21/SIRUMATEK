-- Crear la base de datos SIRUMATEK
CREATE DATABASE SIRUMATEK;

-- Crear tabla Usuario
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(100) NOT NULL
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
    nombre VARCHAR(100) NOT NULL
);

-- Crear tabla Empleado_Departamento para la relación entre Empleado y Departamento
CREATE TABLE empleado_departamento (
    empleado_id INT NOT NULL,
    departamento_id INT NOT NULL,
    PRIMARY KEY (empleado_id, departamento_id),
    FOREIGN KEY (empleado_id) REFERENCES empleado(id) ON DELETE CASCADE,
    FOREIGN KEY (departamento_id) REFERENCES departamento(id) ON DELETE CASCADE
);

-- Crear tabla Tipo_Notificacion
CREATE TABLE tipo_notificacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL
);

-- Crear tabla Notificacion_Correo
CREATE TABLE notificacion_correo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_notificacion INT NOT NULL,
    id_usuario INT NOT NULL,
    id_mensaje VARCHAR(255) NOT NULL,
    id_correo_masivo INT NULL,
    destinatario VARCHAR(255) NOT NULL,
    estatus VARCHAR(10) NOT NULL,
    fecha_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_estatus CHECK (estatus IN ('ENVIADO', 'ERROR', 'PENDIENTE')),
    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE,
    CONSTRAINT fk_tipo_notificacion FOREIGN KEY (id_tipo_notificacion) REFERENCES tipo_notificacion(id) ON DELETE CASCADE
);