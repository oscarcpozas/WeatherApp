package com.study.jam.weather.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.study.jam.weather.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras(); // Obtenemos el Intent con el que se ha iniciado la Activity
        String title = extras.getString("TITLE");

        TextView textView = (TextView) findViewById(R.id.details_title);
        textView.setText(title);
    }

}
