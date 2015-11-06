package com.study.jam.weather.rest;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTP {

    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;

    String forecastJsonStr = null;
    final String API_URL = "HTTP://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7";

    public String getDataforAPI() {
        try {
            URL url = new URL(API_URL);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if(inputStream == null) { return null; }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) { return null; }
            forecastJsonStr = buffer.toString();
        } catch (IOException e) {
            return null;
        } finally {
            if (urlConnection != null) { urlConnection.disconnect(); }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }

        return forecastJsonStr;
    }
}

