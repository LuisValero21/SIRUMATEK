package com.example.sirumatek.service;

import com.example.sirumatek.model.User;
import com.example.sirumatek.model.Employee;
import com.example.sirumatek.repository.EmployeeRepository;
import com.example.sirumatek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Employee registrarEmpleado(Employee empleado) {
        // Guardar empleado en la base de datos
        Employee nuevoEmpleado = employeeRepository.save(empleado);

        // Crear un usuario asociado al empleado
        User usuario = new User();
        usuario.setNombre(empleado.getNombre() + " " + empleado.getApellido());
        usuario.setCorreo(empleado.getCorreo());
        usuario.setContrasena(passwordEncoder.encode("default123")); // Contraseña por defecto
        usuario.setRol("USER"); // Rol por defecto

        userRepository.save(usuario); // Guardar en la tabla Usuario

        return nuevoEmpleado;
    }

    public List<Employee> obtenerTodosLosEmpleados() {
        return employeeRepository.findAll();
    }

    public boolean eliminarEmpleado(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
