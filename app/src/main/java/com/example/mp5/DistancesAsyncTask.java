package com.example.mp5;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.example.lib.LocationData;
import com.google.android.gms.location.LocationServices;

public class DistancesAsyncTask extends AsyncTask<String, String, String> {
    private final String API_KEY = "AIzaSyDkJezIq93KVGDpJarXLIqsxs2-xg7nL9c";
    private Context mainContext;

    public DistancesAsyncTask(Context context) {
        mainContext = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {

            LocationManager lm = (LocationManager) mainContext.getSystemService(mainContext.LOCATION_SERVICE);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            System.out.println("Getting distances in background");

            String url = "https://maps.googleapis.com/maps/api/distancematrix/json?";
            url = url + "origins:" + latitude + "," + longitude;
            url = url + "&destinations=";
            for (String[] lab : LocationData.locationData) {
                url = url + lab[2] + "," + lab[3] + "|";
            }
            url = url.substring(0, url.length() - 1);
            System.out.println(url);
        } catch (SecurityException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Do in background returning");
        return null;
    }
}
