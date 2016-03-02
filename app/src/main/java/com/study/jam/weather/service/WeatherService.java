package com.study.jam.weather.service;

import android.app.IntentService;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.study.jam.weather.R;
import com.study.jam.weather.rest.HTTP;

public class WeatherService extends IntentService {

    public static final String LOCATION_QUERY_EXTRA = "lqe";

    public WeatherService() {
        super("WeatherService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        /*
            Tarea larga que realizamos al recibir un Intent
        */

        String locationQuery = intent.getStringExtra(LOCATION_QUERY_EXTRA);

        final String API_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q="
                + locationQuery
                + "&mode=json&units=metric&cnt=7&appid=381bdcc454a4b9678f11fdb192cca4ad";

        HTTP client = new HTTP();
        String result = client.getDataforAPI(API_URL);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Servicio arrancado!", Toast.LENGTH_SHORT).show();

        Notification mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.heartocat)
                        .setContentTitle("Notificación de Weather")
                        .setContentText("Hello World!")
                        .build();

        startForeground(startId, mBuilder);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    static public class AlarmReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}
