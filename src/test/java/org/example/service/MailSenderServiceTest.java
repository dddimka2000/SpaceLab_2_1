package org.example.service;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import org.example.service.MailSenderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest

public class MailSenderServiceTest {
    private GreenMail greenMail;

    @Autowired
    private MailSenderService mailSenderService;

    @BeforeEach
    public void setup() {
        ServerSetup serverSetup = new ServerSetup(2525, "localhost", "smtp");
        greenMail = new GreenMail(serverSetup);
        greenMail.start();
    }

    @AfterEach
    public void tearDown() {
        greenMail.stop();
    }

    @Test
    public void testSendSimpleMail() throws Exception {
        String mail = "2000-dimk@ukr.net";
        String subject = "Test Subject";
        String content = "Test Content";

        mailSenderService.sendSimpleMail(mail, subject, content);
        Thread.sleep(1000);

    }
}