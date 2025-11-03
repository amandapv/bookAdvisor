package com.example.bookAdvisor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender sender;

    public boolean sendEmail(String destination, String subject, String textMessage) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); //se añade true para indicarle que el correo será "Multipart", ya qu eno es un correo simple (texto plano), sino que adjuntaremos archivos (multipart - contenido complejo)
            helper.setTo(destination);
            helper.setText(textMessage, true);
            helper.setSubject(subject);
            sender.send(message);
            return true;
        }
        catch (MessagingException e) {
            e.printStackTrace();
            return false; 
        }
    } 
}
