package com.example.sirumatek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sirumatek.model.Employee;
import com.example.sirumatek.service.EmployeeService;

@RestController
@RequestMapping("/api/empleados")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/registrar")
    public ResponseEntity<Employee> registrarEmpleado(@RequestBody Employee empleado) {
        Employee nuevoEmpleado = employeeService.registrarEmpleado(empleado);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Employee>> listarEmpleados() {
        List<Employee> empleados = employeeService.obtenerTodosLosEmpleados();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }
}
