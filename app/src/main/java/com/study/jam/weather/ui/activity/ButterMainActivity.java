package com.study.jam.weather.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.study.jam.weather.R;

import butterknife.Bind;

public class ButterMainActivity extends AppCompatActivity {

    /*
     *  No olvidar añadir la libreria en el build.grandle
     */
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.text_view) TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);  //Cargamos nuestro layout XML.

        setSupportActionBar(toolbar);

        textView.setText("Oscar is a hero!");
    }
}
