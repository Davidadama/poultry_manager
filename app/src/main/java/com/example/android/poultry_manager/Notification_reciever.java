package com.example.android.poultry_manager;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by david adama on 1/7/2019.
 */
public class Notification_reciever extends BroadcastReceiver{

    private static Uri alarmSound;
    private int messageCount = 0;
    //Vibration pattern
    private final long[] pattern = {100,300,300,300};

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context,Drugnvacc0Activity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Poultry Manager")
                .setContentText("Click here to view vaccination details")
                .setAutoCancel(true);

        builder.setNumber(++messageCount);
        builder.setSound(alarmSound);
        builder.setVibrate(pattern);

        notificationManager.notify(100,builder.build());
    }
}
