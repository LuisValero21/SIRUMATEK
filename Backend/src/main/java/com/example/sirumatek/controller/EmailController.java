package com.example.sirumatek.controller;

import com.example.sirumatek.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*")  // Permite llamadas desde el frontend
public class EmailController {

    @Autowired
    private EmailService emailService;

    // Definir una clase interna para recibir el body correctamente
    public static class EmailRequest {
        public String destinatario;
        public String nombreDestinatario;
        public String asunto;
        public String templateId;
        public Map<String, String> variables;
    }

    @PostMapping("/enviar-cumpleanos")
    public String enviarCorreoCumpleanos(@RequestBody EmailRequest request) {
        return emailService.enviarCorreo(
                request.destinatario,
                request.nombreDestinatario,
                request.asunto,
                request.templateId,
                request.variables
        );
    }
}