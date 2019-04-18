package com.example.lib;
import java.util.List;
import java.util.ArrayList;

import sun.rmi.runtime.Log;

/**
 * Manage the list of data.
 * Should also retrieve API data and update list.
 * Return specific subsets of the list (favorites).
 */

public class DataManager {
    /** The main list of all data */
    List<LocationItem> locationItems;

    /** Tag for logging */
    private final String TAG = "DataManager";

    public DataManager() {
        createList();
    }

    private void createList() {
        System.out.println("Creating list of locations");
        locationItems = new ArrayList<>();
        for (LocationNames locationName: LocationNames.values()) {
            locationItems.add(new LocationItem(locationName.name().replace('_', ' ')));
        }
        updateData();
    }

    public void updateData() {
        Log.d(TAG, "Updating Data");
        //get api
        //seperate JSON
        //update the list of data with api data
        //update locationitems time, etc.
    }
}
