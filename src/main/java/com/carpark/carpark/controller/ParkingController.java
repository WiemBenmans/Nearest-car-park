package com.carpark.carpark.controller;

import com.carpark.carpark.kafka.KafkaProducer;
import com.carpark.carpark.model.CarParking;
import com.carpark.carpark.spark.SparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// ParkingController.java
@Controller
/*
@RestController
@RequestMapping("/parking")*/
public class ParkingController {

    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private final SparkService sparkService;
    @Autowired
    public ParkingController(KafkaProducer kafkaProducer, SparkService sparkService) {
        this.kafkaProducer = kafkaProducer;
        this.sparkService = sparkService;
    }
    @GetMapping("/parking/sendLocation")
    public String sendLocation(
            @RequestParam("longitude") double longitude,
            @RequestParam("latitude") double latitude,
            Model model) {

        kafkaProducer.sendLocation(longitude, latitude);


        List<CarParking> availableParking = sparkService.getAvailableCarParking(longitude, latitude);


        model.addAttribute("availableParking", availableParking);


        return "availableParking";
    }
}
