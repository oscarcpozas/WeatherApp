package com.study.jam.weather.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.study.jam.weather.R;
import com.study.jam.weather.service.WeatherService;
import com.study.jam.weather.ui.fragment.MainFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Cargamos nuestro layout XML
        ButterKnife.bind(this);  // Injectamos las vistas con ButterKnife

        setSupportActionBar(toolbar);

        initFragment(MainFragment.newInstance());
        startActivity(new Intent(this, SplashActivity.class));
        overridePendingTransition(0,0);                 // Desactivamos la transicción entre pantallas

        /* final String SOME_ACTION = "com.android.mytabs.MytabsActivity.AlarmReceiver";
        IntentFilter intentFilter = new IntentFilter(SOME_ACTION);
        receiver = new WeatherService.AlarmReceiver();
        registerReceiver(receiver, intentFilter); */

        /* Intent intentTardio = new Intent(this, WeatherService.class);
        intentTardio.putExtra("ESTA_ACTIVO", true);
        sendBroadcast(intentTardio); */

        //stopService(intent); 
    }

    public void initFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.refresh:
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                String location = preferences.getString("location", "94043");

                Intent intent = new Intent(this, WeatherService.class);
                intent.putExtra(WeatherService.LOCATION_QUERY_EXTRA, location);
                startService(intent);                           // Arrancamos el Service
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
