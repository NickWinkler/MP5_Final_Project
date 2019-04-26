package com.example.mp5;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.example.lib.LocationData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Manage the list of data.
 * Should also retrieve API data and update list.
 * Return specific subsets of the list (favorites).
 */
public class DataManager extends AsyncTask<MainActivity, String, String> {
    /** The main list of all data */
    private static List<LocationItem> locationItems;
    private static List<LocationItem> favoriteItems;

    private MainActivity mainActivity;

    private static final String url = "https://my.engr.illinois.edu/labtrack/util_data_json.asp?callback=?";

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
                favoriteItems.add(locationItem);
            }
        }
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

    @Override
    protected String doInBackground(MainActivity... mainActivities) {
        //Get the JSON from the URL and return it as a string.
        mainActivity = mainActivities[0];
        System.out.println("Getting JSON data");
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL webUrl = new URL(url);
            connection = (HttpURLConnection) webUrl.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                //Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

            }

            return buffer.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        String toastMessage;
        try {
            String formattedString = string.substring(2, string.length() - 1);
            JSONObject jsonObject = new JSONObject(formattedString);
            JSONArray jsonArray = (JSONArray) jsonObject.get("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject curLocation = jsonArray.getJSONObject(i);
                for (LocationItem locationItem : locationItems) {
                    if (locationItem.getName().equals(curLocation.get("strlabname"))) {
                        locationItem.setMachineCount(curLocation.getInt("machinecount"));
                        locationItem.setMachineUsage(curLocation.getInt("inusecount"));
                    }
                }
            }
            System.out.println(jsonArray);
            toastMessage = "Data Updated";
        } catch (Exception e) {
            e.printStackTrace();
            toastMessage = "Error. Try again later.";
        }
        Toast.makeText(mainActivity, toastMessage, Toast.LENGTH_SHORT).show();
        mainActivity.updateUI();
    }
}