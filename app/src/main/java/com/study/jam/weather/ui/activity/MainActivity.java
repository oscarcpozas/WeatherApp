package com.study.jam.weather.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.study.jam.weather.R;
import com.study.jam.weather.model.Weather;
import com.study.jam.weather.rest.HTTP;
import com.study.jam.weather.rest.WeatherDataParser;
import com.study.jam.weather.ui.adapter.RecyclerAdapter;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String API_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7&appid=381bdcc454a4b9678f11fdb192cca4ad";

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     *   OnCreate method lo sobrescribimos de la clase extendida AppCompatActivity, este
     *   method se llama al momento de crear las vistas en pantalla.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);  // Cargamos nuestro layout XML.

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // Instanciamos nuestra toolbar
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.weather_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ConnectionAPITask apiTask = new ConnectionAPITask(recyclerView, API_URL);
        apiTask.execute(); // Ejecutamos nuestra tarea definida en el Asynctask
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

            return result;
        }

        @Override
        protected void onPostExecute(String result) { // Este metodo se ejecuta en el UI Thread
            super.onPostExecute(result);
            try {
                WeatherDataParser parser = new WeatherDataParser();
                final ArrayList<Weather> data = parser.getWeatherDataFromJson(result);

                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data);
                recyclerView.setAdapter(recyclerAdapter);
            } catch (JSONException e) { e.printStackTrace(); }
        }
    }
}
