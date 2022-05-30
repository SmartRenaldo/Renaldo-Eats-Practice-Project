package com.renaldo.service.impl;

import com.renaldo.common.CustomException;
import com.renaldo.service.MailService;
import com.renaldo.utils.ValidationCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private static final String SENDER = "renaldoeatsprojectonly@gmail.com";

    @Override
    public void send(String receiver, String subject, String content) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(SENDER + "(RENALDO EATS)");
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new CustomException("Send failed!");
        }
    }

    @Override
    public String sendVerificationCode4Digits(String email) {
        String subject = "RENALDO EATS verification code";
        String code = ValidationCodeUtils.generateValidateCode(4).toString();
        String content = "[RENALDO EATS]  Your verification code is " + code;
        send(email, subject, content);

        return code;
    }
}
