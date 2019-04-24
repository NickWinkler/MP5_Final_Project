package com.example.lib;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Manage the list of data.
 * Should also retrieve API data and update list.
 * Return specific subsets of the list (favorites).
 */
public class DataManager {
    /** The main list of all data */
    private static List<LocationItem> locationItems;

    /** Tag for logging */
    private static final String TAG = "DataManager";

    private static Random random = new Random();

    public static void create() {
        locationItems = new ArrayList<>();
        for (LocationNames locationName: LocationNames.values()) {
            int usage = random.nextInt(36);
            locationItems.add(new LocationItem(locationName.name().replace('_', ' '), 0, 0,
                    random.nextInt(15), usage, (int) (usage * random.nextFloat() + usage)));
        }
        //updateData();
    }

    public static List<LocationItem> getLocationItems() {
        System.out.println("Returning locations");
        return locationItems;
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
