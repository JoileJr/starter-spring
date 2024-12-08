package com.starter.spring.service.email;

import jakarta.mail.MessagingException;

public interface EmailService {
    void enviarEmailComAnexo(Long id) throws MessagingException;
}
