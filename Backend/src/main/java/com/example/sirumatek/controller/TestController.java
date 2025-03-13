package com.example.sirumatek.controller;

import com.example.sirumatek.model.Employee;
import com.example.sirumatek.repository.EmployeeRepository;
import com.example.sirumatek.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private EmployeeRepository employeeRepository;
    private EmailService emailService;

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test endpoint");
    }

    @GetMapping("/cumpleanos")
    public List<Employee> obtenerCumpleanerosHoy() {
        LocalDate today = LocalDate.now();
        return employeeRepository.findByMonthAndDay(today.getMonthValue(), today.getDayOfMonth());
    }

    @GetMapping("/enviar-cumpleanos")
    public String testEnvioCorreos() {
        emailService.enviarCorreosCumpleanos();
        return "Proceso de verificación ejecutado.";
    }
}