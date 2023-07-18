package org.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest

public class MailSenderServiceTest {

    @Autowired
    private MailSenderService mailSenderService;

    @Test
    public void testSendSimpleMail() throws Exception {
        String mail = "2000-dimk@ukr.net";
        String subject = "Test Subject";
        String content = "Test Content";

        mailSenderService.sendSimpleMail(mail, subject, content);
        Thread.sleep(1000);

    }
}