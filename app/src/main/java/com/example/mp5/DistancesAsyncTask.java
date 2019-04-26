package com.example.mp5;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.example.lib.LocationData;
import com.google.android.gms.location.LocationServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

            String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&mode=walking";
            url = url + "&origins=" + latitude + "," + longitude;
            url = url + "&destinations=";
            for (String[] lab : LocationData.locationData) {
                url = url + lab[2] + "," + lab[3] + "|";
            }
            url = url.substring(0, url.length() - 1);
            url = url + "&key=" + API_KEY;
            System.out.println(url);


            System.out.println("Getting JSON data");
            HttpURLConnection connection = null;
            BufferedReader reader = null;

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

        } catch (SecurityException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Do in background returning");
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
}
