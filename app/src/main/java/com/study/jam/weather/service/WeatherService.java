package com.study.jam.weather.service;

import android.app.IntentService;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.study.jam.weather.R;

public class WeatherService extends IntentService {

    public WeatherService() {
        super("SoyUnServicio");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        /*
        LONG TASK
         */
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        Notification mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.heartocat)
                        .setContentTitle("My notification")
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
