package com.dicoding.picodiploma.mybroadcastreceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    Button btnDownload;
    //public static final String ACTION_DOWNLOAD_STATUS = "download_status";
    private BroadcastReceiver downloadReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDownload = (Button) findViewById(R.id.btn_download);
        btnDownload.setOnClickListener(this);

        downloadReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Toast.makeText(context, "Download Selesai", Toast.LENGTH_SHORT).show();
            }
        };

        IntentFilter downloadIntentFilter = new IntentFilter("download_status");
        registerReceiver(downloadReceiver, downloadIntentFilter);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_download) {
            //Intent in=new Intent(getApplicationContext(),MainAct2.class);
            //startActivity(in);

            //utama
            //Intent downloadServiceIntent = new Intent(this, DownloadService.class);
            //startService(downloadServiceIntent);
            startAlertAtParticularTime();


        }
    }

    Intent intent;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    public void startAlertAtParticularTime() {
        intent = new Intent(this, DownloadService.class);
        pendingIntent = PendingIntent.getService(
                this.getApplicationContext(), 280192, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(SystemClock.elapsedRealtime());

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(),
                60000, pendingIntent);

        Toast.makeText(this, "Alarm will vibrate at time specified",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downloadReceiver != null) {
            unregisterReceiver(downloadReceiver);
        }
    }
}