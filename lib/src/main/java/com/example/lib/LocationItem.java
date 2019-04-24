package com.example.lib;

public class LocationItem {
    private String name;
    private double latitude;
    private double longitude;
    private int time;
    private int machineUsage;
    private int machineCount;
    private boolean isFavorites;

    LocationItem(final String setName, double setLatitude, double setLongitude, int setTime,
                 int setMUsage, int setMCount, boolean setFavorite) {
        name = setName;
        latitude = setLatitude;
        longitude = setLongitude;
        time = setTime;
        machineUsage = setMUsage;
        machineCount = setMCount;
        isFavorites = setFavorite;
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

    public boolean getIsFavorite() {
        return isFavorites;
    }

    public void flipFavorites() {
        isFavorites = !isFavorites;
    }
}
