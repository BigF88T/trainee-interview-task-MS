package ru.sergey.moysklad.kafka_service.services;


import jakarta.mail.MessagingException;
import org.apache.kafka.common.protocol.types.Field;

import java.io.FileNotFoundException;

public interface EmailService {

    void sendSimpleEmail(final String toAddress, final String subject, final String message);
    void sendEmailWithAttachment(final String toAddress, final String subject, final String message, final String attachment) throws MessagingException, FileNotFoundException;

}
