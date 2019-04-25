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
    private static List<LocationItem> favoriteItems;

    /** Tag for logging */
    private static final String TAG = "DataManager";

    private static Random random = new Random();

    public static void create() {
        locationItems = new ArrayList<>();
        favoriteItems = new ArrayList<>();
        for (Object[] location : LocationData.locationData) {
            int usage = random.nextInt(36);
            LocationItem locationItem = new LocationItem(location[0].toString(), 0, 0,
                    random.nextInt(15), usage, (int) (usage * random.nextFloat() + usage), random.nextBoolean());
            locationItems.add(locationItem);
            // Add items to favorites list here
            if (locationItem.getIsFavorite()) {
//                favoriteItems.add(locationItem);
            }
        }
        //updateData();
    }

    public static List<LocationItem> getLocationItems() {
        return locationItems;
    }

    public static List<LocationItem> getFavoriteItems() {
        return favoriteItems;
    }

    public static void removeFavorite(int position) {
        favoriteItems.remove(position);
    }

    public static void addFavorite(LocationItem newFav) {
        favoriteItems.add(newFav);
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
