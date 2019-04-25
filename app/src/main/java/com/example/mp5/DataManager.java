package com.example.mp5;
import android.os.AsyncTask;

import com.example.lib.LocationData;

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

    String url = "https://my.engr.illinois.edu/labtrack/util_data_json.asp?callback=?";

    /** Tag for logging */
    private static final String TAG = "DataManager";

    private static Random random = new Random();

//    class MyAsyncTask extends AsyncTask<String, String, String>{}

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
                favoriteItems.add(locationItem);
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
        System.out.println("updating data");


    }

    private class DataFetcher extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            return null;
        }
    }
}
