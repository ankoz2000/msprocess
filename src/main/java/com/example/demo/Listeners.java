package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Listeners {

    private final String topicName = "msprocess";

    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper om;

    @Autowired
    public Listeners(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper om) {
        this.kafkaTemplate = kafkaTemplate;
        this.om = om;
    }

    @KafkaListener(topics = "mscashdesc", id = "0")
    public void cashDescListener(String data) {
        translateToTopic(topicName, data);
    }

    public void translateToTopic(String topicName, String transactionData) {
        kafkaTemplate.send(topicName, transactionData);
    }
}
