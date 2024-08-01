package com.carpark.carpark.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC = "parking_location";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendLocation(double longitude, double latitude) {
        String message = String.format("%f,%f", longitude, latitude);
        kafkaTemplate.send(TOPIC, message);
    }
}
