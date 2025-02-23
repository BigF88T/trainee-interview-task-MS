package ru.sergey.moysklad.kafka_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "notification", groupId = "notific_service")
    public void listen(String msg) {
        System.out.println("Received message: " + msg);
    }

}
