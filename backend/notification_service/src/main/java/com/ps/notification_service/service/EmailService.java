package com.ps.notification_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    @Value("${SMTP_FROM}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;

    public boolean sendEmail(String to,String subject,String body){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            javaMailSender.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }
}
