package com.example.hp.chitchat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

public class NotificationUtils  {
    private static final int NOTIFICATION_ID= 4242;
    private static final int PENDING_ID=1324;
    private static final String CHANNEL_ID="reminder_notification_channel";
    // TODO (7) Create a method called remindUserBecauseCharging which takes a Context.
    public static void remindUser(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID,"Channel Name",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setColor(ContextCompat.getColor(context,R.color.colorPrimary))
                .setSmallIcon(R.drawable.chat)
                .setContentTitle("ChitChat")
                .setContentText("New Message")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("New Message"))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT<Build.VERSION_CODES.O){
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(NOTIFICATION_ID,notificationBuilder.build());
    }
    public static PendingIntent contentIntent(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        return PendingIntent.getActivity(context,PENDING_ID,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
