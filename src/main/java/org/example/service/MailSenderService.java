package org.example.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Log4j2
@Service
public class MailSenderService {
    private final
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String usernameMail;

    @Autowired
    public MailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @Async
    public void sendSimpleMail(String mail, String subject, String htmlContent) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(usernameMail);
            helper.setTo(mail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            log.info(mail+" was successful");
        } catch (MessagingException e) {
            log.error(mail+" sending failed");
            throw new RuntimeException(e);
        }
        javaMailSender.send(message);
    }

    public void setUsernameMail(String usernameMail) {
        this.usernameMail = usernameMail;
    }

    public String getUsernameMail() {
        return usernameMail;
    }


}
