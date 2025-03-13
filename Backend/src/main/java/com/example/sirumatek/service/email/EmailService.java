package com.example.sirumatek.service.email;

import com.example.sirumatek.model.Employee;
import com.example.sirumatek.repository.EmployeeRepository;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${sendgrid.sender.email}")
    private String senderEmail;

    @Value("${sendgrid.sender.name}")
    private String senderName;

    private final EmployeeRepository employeeRepository;

    public EmailService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public String enviarCorreo(String destinatario, String nombreDestinatario, String asunto, String templateId, Map<String, String> variables) {
        try {
            System.out.println("Variables enviadas a SendGrid: " + variables);

            Email from = new Email(senderEmail, senderName);
            Email to = new Email(destinatario, nombreDestinatario);
            Mail mail = new Mail();
            mail.setFrom(from);
            mail.setSubject(asunto);
            mail.setTemplateId(templateId);

            Personalization personalization = new Personalization();
            personalization.addTo(to);

            if (variables != null) {
                variables.forEach(personalization::addDynamicTemplateData);
            }

            mail.addPersonalization(personalization);

            SendGrid sg = new SendGrid(sendGridApiKey);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            System.out.println("Código de respuesta SendGrid: " + response.getStatusCode());
            System.out.println("Cuerpo de respuesta: " + response.getBody());

            return response.getStatusCode() == 202 ? "Correo enviado con éxito" : "Error al enviar correo: " + response.getBody();
        } catch (IOException ex) {
            return "Error al enviar correo: " + ex.getMessage();
        }
    }

    @Scheduled(cron = "0 15 11 * * ?")
    public void enviarCorreosCumpleanos() {
        LocalDate today = LocalDate.now();
        int mesActual = today.getMonthValue();
        int diaActual = today.getDayOfMonth();

        List<Employee> cumpleaneros = employeeRepository.findByMonthAndDay(mesActual, diaActual);

        if (!cumpleaneros.isEmpty()) {
            for (Employee empleado : cumpleaneros) {
                String asunto = "🎉 ¡Feliz cumpleaños, " + empleado.getNombre() + "! 🎂";
                String templateId = "d-96fcfb13970f4d81b7bd8980786501bc";

                Map<String, String> variables = new HashMap<>();
                variables.put("nombre", empleado.getNombre());
                variables.put("mensaje", "Esperamos que tengas un gran día lleno de alegría y éxito.");

                enviarCorreo(empleado.getCorreo(), empleado.getNombre(), asunto, templateId, variables);
            }
            System.out.println("Correos de cumpleaños enviados a: " + cumpleaneros.size() + " empleados.");
        } else {
            System.out.println("Hoy no hay empleados de cumpleaños.");
        }
    }
}