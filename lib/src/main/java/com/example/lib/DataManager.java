package com.example.lib;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;

/**
 * Manage the list of data.
 * Should also retrieve API data and update list.
 * Return specific subsets of the list (favorites).
 */
public class DataManager {
    /** The main list of all data */
    List<LocationItem> locationItems;

    /** Tag for logging */
    private static final String TAG = "DataManager";

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
        //get api
        //seperate JSON
        //update the list of data with api data
        //update locationitems time, etc.

        // Get the
        /*
        try {
            URL url = new URL("http://example.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } */
    }
}
