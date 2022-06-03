package com.renaldo.test;

import com.renaldo.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class RenaldoEatsApplicationTests {

    @Autowired
    private MailService mailService;

    @Test
    void getSuffixFileTest() {
        String fileName = "qefqe.qe223fqe.qe";
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffix);
    }

    @Test
    void sendEmailTest() {
        String receiver = "shiba_inu_dao@outlook.com";
        String subject = "RENALDO EATS verification code";
        String content = "[RENALDO EATS]  Your verification code is 245336";
        mailService.send(receiver, subject, content);
    }

    /**
     * BigDecimal.valueOf() is better than new BigDecimal()
     */
    @Test
    void testBigDecimal() {
        double d = 0.9989999999999999D;
        System.out.println(new BigDecimal(d));
        System.out.println(BigDecimal.valueOf(d));
        System.out.println("0.9989999999999999");

        double d2 = 0.998999999999999911D;
        System.out.println(new BigDecimal(d2));
        System.out.println(BigDecimal.valueOf(d2));
        System.out.println("0.998999999999999911");
    }

}
