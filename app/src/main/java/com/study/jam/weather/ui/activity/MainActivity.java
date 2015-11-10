package com.study.jam.weather.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.study.jam.weather.R;
import com.study.jam.weather.model.Weather;
import com.study.jam.weather.ui.adapter.RecyclerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
    }

    /*
     *   OnCreate method lo sobrescribimos de la clase extendida AppCompatActivity, este
     *   method se llama al momento de crear las vistas en pantalla.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);  //Cargamos nuestro layout XML.

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //Instanciamos nuestra toolbar
        setSupportActionBar(toolbar);

        ArrayList<Weather> data = new ArrayList<>();
        for(int i=0; i<30; i++)
            data.add(new Weather().setTitle("Tiempo en ciudad " + i));

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.weather_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(recyclerAdapter);
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
}
