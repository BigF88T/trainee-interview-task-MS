package ru.sergey.moysklad.kafka_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import ru.sergey.moysklad.kafka_service.services.EmailService;

@Service
public class KafkaConsumer {

    private final EmailService emailService;

    @Value("${spring.mail.username}")
    private String email;
    @Autowired
    public KafkaConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "notification", groupId = "notific_service")
    public void listen(String msg) {
        try {
            emailService.sendSimpleEmail(email, "New delivery", msg);
        } catch (MailException e) {
            throw new RuntimeException(e);
        }
    }
}