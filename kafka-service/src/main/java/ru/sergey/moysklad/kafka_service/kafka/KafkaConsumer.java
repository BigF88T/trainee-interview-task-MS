package ru.sergey.moysklad.kafka_service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "kafkaService", groupId = "simple_consumer")
    public void listen(String msg) {
        System.out.println("Received message: " + msg);
    }

}
