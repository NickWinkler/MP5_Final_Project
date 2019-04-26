package com.example.mp5;

public class LocationItem {
    private String name;
    private double latitude;
    private double longitude;
    private String time;
    private int machineUsage;
    private int machineCount;
    private boolean isFavorites;

    LocationItem(final String setName, double setLatitude, double setLongitude, String setTime,
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

    public String getTime() {
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

    public void setTime(String setTime) {time = setTime;}

    public void setMachineUsage(int setMachineUsage) {
        machineUsage = setMachineUsage;
    }

    public void setMachineCount(int setMachineCount) {
        machineCount = setMachineCount;
    }

    public void flipFavorites() {
        isFavorites = !isFavorites;
    }
}
