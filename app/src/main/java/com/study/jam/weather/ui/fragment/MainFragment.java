package com.study.jam.weather.ui.fragment;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.jam.weather.R;
import com.study.jam.weather.helper.WeatherProvider;
import com.study.jam.weather.model.Weather;
import com.study.jam.weather.rest.HTTP;
import com.study.jam.weather.rest.WeatherDataParser;
import com.study.jam.weather.ui.adapter.RecyclerAdapter;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // La logica de insert esta vacia por tanto esto no hara nada que podamos apreciar
        ContentValues contentValues = new ContentValues();
        contentValues.put("MyKEY", "VALUE");

        ContentResolver contentResolver = getContext().getContentResolver();
        contentResolver.insert(WeatherProvider.CONTENT_URI, contentValues);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String location = preferences.getString("location", "94043");

        final String API_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q="
                + location
                + "&mode=json&units=metric&cnt=7&appid=381bdcc454a4b9678f11fdb192cca4ad";

        ConnectionAPITask apiTask = new ConnectionAPITask(recyclerView, API_URL);
        apiTask.execute(); // Ejecutamos nuestra tarea definida en el Asynctask

        return rootView;
    }

    private void sendBroadCast(Context context) {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("FINISH_SPLASH_ACTIVITY");
        context.sendBroadcast(broadcastIntent);
    }

    /**
     * Como no podemos realizar tareas que interrumpan el UI Thread, debemos ejecutar la
     * petición a la API en un hilo paralelo, los Asynctask nos ayudan con esta tarea.
     * Aquí definimos nuestro Asynctask donde tras obtener la información de la API y tratarla
     * con el WeatherDataParser, se la pasamos al RecyclerView para que la muestre en pantalla.
     */

    public class ConnectionAPITask extends AsyncTask<Void, Void, String> { // AsyncTask<Parametros, Progreso, Resultado> -- Son los tipos con los que trabajara el Asynctask

        private String url;
        private RecyclerView recyclerView;

        public ConnectionAPITask(RecyclerView recyclerView, String url) {
            this.url = url;
            this.recyclerView = recyclerView;
        }

        @Override
        protected void onPreExecute() { // Este metodo se ejecuta en el UI Thread
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) { // Este metodo se ejecuta en Background
            HTTP client = new HTTP();
            String result = client.getDataforAPI(url);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) { e.printStackTrace(); }

            return result;
        }

        @Override
        protected void onPostExecute(String result) { // Este metodo se ejecuta en el UI Thread
            super.onPostExecute(result);
            try {
                WeatherDataParser parser = new WeatherDataParser();
                final ArrayList<Weather> data = parser.getWeatherDataFromJson(result);

                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), data);
                recyclerView.setAdapter(recyclerAdapter);

                sendBroadCast(getContext());
            } catch (JSONException e) { e.printStackTrace(); }
        }
    }
}
