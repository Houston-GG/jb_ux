package com.example.jb_ux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailDispatchService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailAddress;

    @Value("${mail.alias}")
    private String alias;

    @Autowired
    public MailDispatchService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void makeMailDispatch(String[] emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(mailAddress);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
    public void makeMailDispatch(String[] emailTo, String[] emailCc, String[] emailBcc, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(alias + "<" + mailAddress + ">");
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setCc(emailCc);
        mailMessage.setBcc(emailBcc);

        try {
            mailSender.send(mailMessage);
        } catch (MailSendException e) {
            e.printStackTrace();
        }

    }
}
