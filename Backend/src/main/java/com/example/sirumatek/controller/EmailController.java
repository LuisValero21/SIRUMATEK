package com.example.sirumatek.controller;

import com.example.sirumatek.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*")  // Permite llamadas desde el frontend
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarCorreo(@RequestBody Map<String, Object> request) {
        String destinatario = (String) request.get("destinatario");
        String asunto = (String) request.get("asunto");
        String templateId = (String) request.get("templateId");
        Map<String, String> variables = (Map<String, String>) request.get("variables");

        String resultado = emailService.enviarCorreo(destinatario, asunto, templateId, variables);
        return ResponseEntity.ok(resultado);
    }
}