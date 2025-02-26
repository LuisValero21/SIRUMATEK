package com.example.sirumatek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.sirumatek.model.Employee;
import com.example.sirumatek.service.EmployeeService;

@CrossOrigin(origins = "*")
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

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Long id) {
        boolean eliminado = employeeService.eliminarEmpleado(id);
        if (eliminado) {
            return ResponseEntity.ok("Empleado eliminado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado.");
        }
    }
}
