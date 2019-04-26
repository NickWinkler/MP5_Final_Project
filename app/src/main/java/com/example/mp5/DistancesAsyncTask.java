package com.example.mp5;

import android.os.AsyncTask;

import com.example.lib.LocationData;

import java.net.URL;

public class DistancesAsyncTask extends AsyncTask<String, String, String> {
    private final String API_KEY = "";

    @Override
    protected String doInBackground(String... strings) {
        try {
            String url = "https://maps.googleapis.com/maps/api/distancematrix/json?";
            url  = url + "origins:" + ;
            url = url + "&destinations="
            for (String[] location : LocationData.locationData) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
