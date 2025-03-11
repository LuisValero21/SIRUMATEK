package com.example.sirumatek.service.email;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class EmailService {

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${sendgrid.sender.email}")
    private String senderEmail;

    @Value("${sendgrid.sender.name}")
    private String senderName;

    public String enviarCorreo(String destinatario, String nombreDestinatario, String asunto, String templateId, Map<String, String> variables) {
        try {
            if (destinatario == null || asunto == null || templateId == null) {
                return "Error: Campos obligatorios faltantes";
            }

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
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                if (response.getStatusCode() == 202) {
                    return "Correo enviado con éxito";
                } else {
                    return "Error al enviar correo: " + response.getBody();
                }
            } catch (IOException ex) {
                return "Error al enviar correo: " + ex.getMessage();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}