package com.ps.user_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl {

    @Value("${SMTP_FROM}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;

    public boolean sendEmail(String to,String subject,String body){
        boolean isSend = false;
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            javaMailSender.send(message);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }
}
