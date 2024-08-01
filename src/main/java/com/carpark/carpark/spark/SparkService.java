package com.carpark.carpark.spark;

import com.carpark.carpark.model.CarParking;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SparkService {

    private final List<CarParking> carParkingList;
    public SparkService() {
        this.carParkingList = loadCarParkingData();
    }
    private List<CarParking> loadCarParkingData() {
        List<CarParking> carParkingList = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getResourceAsStream("/dataset.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));


            List<String> lines = reader.lines().skip(1).collect(Collectors.toList());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (String line : lines) {
                String[] parts = line.split(",");
                CarParking carParking = new CarParking();
                carParking.setSystemCodeNumber(parts[0]);
                carParking.setCapacity(Integer.parseInt(parts[1]));
                carParking.setOccupancy(Integer.parseInt(parts[2]));
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    carParking.setLastUpdated(dateFormat.parse(parts[3]));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                carParking.setLongitude(Double.parseDouble(parts[4]));
                carParking.setLatitude(Double.parseDouble(parts[5]));
                carParkingList.add(carParking);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carParkingList;
    }


    public List<CarParking> getAvailableCarParking(double userLongitude, double userLatitude) {
        return carParkingList.stream()
                .filter(carParking -> carParking.getOccupancy() < carParking.getCapacity())
                .min(Comparator.comparingDouble(carParking ->
                        calculateDistance(userLatitude, userLongitude, carParking.getLatitude(), carParking.getLongitude())))
                .map(Collections::singletonList)
                .orElse(Collections.emptyList());

    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        return Math.sqrt(Math.pow(lat1 - lat2, 2) + Math.pow(lon1 - lon2, 2));
    }
}
