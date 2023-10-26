package com.example.android.poultry_manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import java.util.Calendar;

public class drugnvaacActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugnvaac1);

        findViewById(R.id.notify).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View view) {
                                                              Calendar calendar = Calendar.getInstance();

                                                              calendar.set(Calendar.HOUR_OF_DAY,13);
                                                              calendar.set(Calendar.MINUTE, 00);
                                                              calendar.set(Calendar.SECOND, 0);

                                                              Intent intent = new Intent(getApplicationContext(), Notification_reciever.class);
                                                              PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                                                              AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                              alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

                                                          }
                                                      }
        );

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
    public void drugnvacc0(View view) {
        Intent intent = new Intent(this,Drugnvacc0Activity.class);
        intent.putExtra("fragment_index_key", 0);
        startActivity(intent);
    }
    public void drugnvacc1(View view) {
        Intent intent = new Intent(this,Drugnvacc0Activity.class);
        intent.putExtra("fragment_index_key", 1);
        startActivity(intent);
    }
    public void drugnvacc2(View view) {
        Intent intent = new Intent(this,Drugnvacc0Activity.class);
        intent.putExtra("fragment_index_key", 2);
        startActivity(intent);
    }
}

