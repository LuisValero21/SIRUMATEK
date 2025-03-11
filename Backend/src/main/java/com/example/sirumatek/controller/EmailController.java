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

    @PostMapping("/enviar-cumpleanos")
    public String enviarCorreoCumpleanos(@RequestParam String destinatario, @RequestParam String nombreDestinatario) {
        String asunto = "¡Feliz cumpleaños!";
        String templateId = "d-96fcfb13970f4d81b7bd8980786501bc";

        Map<String, String> variables = new HashMap<>();
        variables.put("nombre", nombreDestinatario);

        return emailService.enviarCorreo(destinatario, nombreDestinatario, asunto, templateId, variables);
    }
}