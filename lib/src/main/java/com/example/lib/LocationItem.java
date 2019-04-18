package com.example.lib;

import javax.xml.stream.Location;

public class LocationItem {
    private String name;
    private Object coordinates;
    private int time;
    private int machineUsage;
    private int machineCount;

    LocationItem(final String setName) {
        name = setName;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return 0;
    }

    public int getMachineUsage() {
        return 0;
    }

    public int getMachineCount() {
        return 0;
    }
}
