package com.study.jam.weather.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.study.jam.weather.R;
import com.study.jam.weather.model.Weather;
import com.study.jam.weather.ui.adapter.RecyclerAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ButterMainActivity extends AppCompatActivity {

    /*
     *  No olvidar añadir la libreria en el build.grandle
     */

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.weather_list) RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //Cargamos nuestro layout XML.
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ArrayList<Weather> data = new ArrayList<>();
        for(int i=0; i<30; i++)
            data.add(new Weather().setTitle("Tiempo en ciudad " + i));

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(recyclerAdapter);
    }
}
