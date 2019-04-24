package com.example.lib;

import javax.xml.stream.Location;

public class LocationItem {
    private String name;
    private Object coordinates;
    private int time;
    private int machineUsage;
    private int machineCount;

    LocationItem(final String setName, Object setCoord, int setTime, int setMUsage, int setMCount) {
        name = setName;
        coordinates = setCoord;
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
}
