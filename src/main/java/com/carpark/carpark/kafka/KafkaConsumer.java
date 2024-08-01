package com.carpark.carpark.kafka;

import com.carpark.carpark.spark.SparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



@Service
public class KafkaConsumer {
    @Autowired
    private SparkService sparkService;

    @KafkaListener(topics = "parking_location", groupId = "group_id")
    public void consume(String message) {
        try {
            String[] parts = message.split(",");
            double longitude = Double.parseDouble(parts[0]);
            double latitude = Double.parseDouble(parts[1]);


            sparkService.getAvailableCarParking(longitude, latitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
