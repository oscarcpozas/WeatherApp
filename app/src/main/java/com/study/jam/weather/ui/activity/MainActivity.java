package com.study.jam.weather.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.study.jam.weather.R;

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

        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText("Oscar is a hero!");
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
