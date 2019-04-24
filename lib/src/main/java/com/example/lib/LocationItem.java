package com.example.lib;

import javax.xml.stream.Location;

public class LocationItem {
    private String name;
    private double latitude;
    private double longitude;
    private int time;
    private int machineUsage;
    private int machineCount;

    LocationItem(final String setName, double setLatitude, double setLongitude, int setTime,
                 int setMUsage, int setMCount) {
        name = setName;
        latitude = setLatitude;
        longitude = setLongitude;
        time = setTime;
        machineUsage = setMUsage;
        machineCount = setMCount;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public int getMachineUsage() {
        return machineUsage;
    }

    public int getMachineCount() {
        return machineCount;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
