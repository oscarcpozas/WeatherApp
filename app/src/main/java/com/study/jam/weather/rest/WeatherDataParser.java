package com.study.jam.weather.rest;

import android.text.format.Time;

import com.study.jam.weather.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class WeatherDataParser {
    private ArrayList<Weather> data = new ArrayList<>();

    private String getReadableDateString(long time) {
        SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
        return shortenedDateFormat.format(time);
    }

    /**
     * En este metodo convertimos nuestro JSON que tenemos almacenado en un String
     * en un ArrayList de obejtos Weather, para poder pasarselo al adapter del
     * RecyclerView.
     */
    public ArrayList<Weather> getWeatherDataFromJson(String forecastJsonStr)
            throws JSONException {

        // Cada una de las variables corresponde con el nombre que tiene los valores en el JSON
        final String OWM_LIST = "list";
        final String OWM_WEATHER = "weather";
        final String OWM_TEMPERATURE = "temp";
        final String OWM_MAX = "max";
        final String OWM_MIN = "min";
        final String OWM_DESCRIPTION = "main";

        JSONObject forecastJson = new JSONObject(forecastJsonStr); // Parseamos el String para convertirlo en JSONObject
        JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);

        Time dayTime = new Time();
        dayTime.setToNow();

        int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);

        dayTime = new Time();

        for(int i = 0; i < weatherArray.length(); i++) {
            String day, description;

            // Obtenemos del Array el JSONObject con el que vamos a trabajar
            JSONObject dayForecast = weatherArray.getJSONObject(i);

            // Obtenemos del JSONObject la fecha, y la convertimos a un formato legible
            long dateTime = dayTime.setJulianDay(julianStartDay+i);
            day = getReadableDateString(dateTime);

            // Obtenemos la descripcion del tiempo que hace, ex: Rain
            JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            description = weatherObject.getString(OWM_DESCRIPTION);

            // Obtemos las maximas y minimas temperaturas del JSONObject
            JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
            long high = Math.round(temperatureObject.getDouble(OWM_MAX)); //Redondeamos las unidades con Math.round() para no tener decimales
            long low = Math.round(temperatureObject.getDouble(OWM_MIN));

            String highLowStr = high + "/" + low;
            data.add(new Weather().setTitle(day + " - " + description + " - " + highLowStr));
        }

        return data;
    }
}
