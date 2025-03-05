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

    public String enviarCorreo(String destinatario, String asunto, String templateId, Map<String, String> variables) {
        try {
            Mail mail = new Mail();
            mail.setFrom(new Email(senderEmail, senderName));
            mail.setSubject(asunto);
            mail.setTemplateId(templateId);

            Personalization personalization = new Personalization();
            personalization.addTo(new Email(destinatario));

            // Agregar variables dinámicas a la plantilla
            for (Map.Entry<String, String> entry : variables.entrySet()) {
                personalization.addDynamicTemplateData(entry.getKey(), entry.getValue());
            }

            mail.addPersonalization(personalization);

            SendGrid sg = new SendGrid(sendGridApiKey);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            if (response.getStatusCode() == 202) {
                return "Correo enviado con éxito";
            } else {
                return "Error al enviar correo: " + response.getBody();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al enviar correo", e);
        }
    }
}
