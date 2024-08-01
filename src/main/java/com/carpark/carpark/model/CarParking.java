package com.carpark.carpark.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarParking implements Serializable {

    private String systemCodeNumber;
    private int capacity;
    private int occupancy;
    private Date lastUpdated;
    private double longitude;
    private double latitude;



    @Override
    public String toString() {
        return "CarParking{" +
                "systemCodeNumber='" + systemCodeNumber + '\'' +
                ", capacity=" + capacity +
                ", occupancy=" + occupancy +
                ", lastUpdated=" + lastUpdated +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getSystemCodeNumber() {
        return systemCodeNumber;
    }

    public void setSystemCodeNumber(String systemCodeNumber) {
        this.systemCodeNumber = systemCodeNumber;
    }
}