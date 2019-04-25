package com.example.lib;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

/**
 * Manage the list of data.
 * Should also retrieve API data and update list.
 * Return specific subsets of the list (favorites).
 */
public class DataManager extends CompletableFuture.AsynchronousCompletionTask {
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
        updateData();
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

    public static void updateData() {
        //get api
        //seperate JSON
        //update the list of data with api data
        //update locationitems time, etc.

        // Get the json
        String json = jsonGetRequest("https://my.engr.illinois.edu/labtrack/util_data_json.asp?callback=?");
        System.out.println(json);
        /*
        try {
            URL url = new URL("https://my.engr.illinois.edu/labtrack/util_data_json.asp?callback=?");
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
        }*/
    }

    public static String jsonGetRequest(String urlQueryString) {
        String json = null;
        try {
            URL url = new URL(urlQueryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = new Scanner(inStream, "UTF-8").useDelimiter("\\Z").next();// input stream to string
        } catch (Exception ex) {
            System.out.println("Can't get EWS data: " + ex);
            ex.printStackTrace();
        }
        return json;
    }
}
