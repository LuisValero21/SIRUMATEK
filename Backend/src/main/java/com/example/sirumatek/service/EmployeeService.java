package com.example.sirumatek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sirumatek.repository.EmployeeRepository;
import com.example.sirumatek.model.Employee;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee registrarEmpleado(Employee empleado) {
        return employeeRepository.save(empleado);
    }

    public List<Employee> obtenerTodosLosEmpleados() {
        return employeeRepository.findAll();
    }
}
