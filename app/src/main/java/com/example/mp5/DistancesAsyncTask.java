package com.example.mp5;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonWriter;
import android.view.Gravity;
import android.widget.Toast;

import com.example.lib.LocationData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DistancesAsyncTask extends AsyncTask<String, String, String> {
    private final String API_KEY = "AIzaSyDkJezIq93KVGDpJarXLIqsxs2-xg7nL9c";
    private MainActivity mainActivity;

    public DistancesAsyncTask(MainActivity setMainActivity) {
        mainActivity = setMainActivity;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            LocationManager lm = (LocationManager) mainActivity.getSystemService(mainActivity.LOCATION_SERVICE);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            DataManager.setCurLatitude(latitude);
            DataManager.setCurLongitude(longitude);
            System.out.println("Currently at " + longitude + ", " + longitude);

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
        String toastMessage;
        try {
            JSONArray jsonArray = new JSONObject(s).getJSONArray("rows").getJSONObject(0).getJSONArray("elements");
            List<LocationItem> tempList = DataManager.getLocationItems();
            for (int i = 0; i < tempList.size(); i++) {
                tempList.get(i).setTime((String) jsonArray.getJSONObject(i).getJSONObject("duration").get("text"));
            }
            toastMessage = "Data Updated";
        } catch (Exception e) {
            e.printStackTrace();
            toastMessage = "Error. Try again later.";
        }
        mainActivity.updateUI(toastMessage);
    }
}
