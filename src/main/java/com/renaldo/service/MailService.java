package com.renaldo.service;

public interface MailService {
    void send(String receiver, String subject, String content);
}
