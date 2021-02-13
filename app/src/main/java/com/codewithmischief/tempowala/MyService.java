package com.codewithmischief.tempowala;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyService extends FirebaseMessagingService {
    private static final String TAG ="Tag";
    FirebaseAuth fAuth;
    public MyService() {
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        fAuth=FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null){
            Intent intent = new Intent(this, MainActivity2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pi = PendingIntent.
                    getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            String Channel_Id = "Default";
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Channel_Id);
            builder.setSmallIcon(R.drawable.ic_notify)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setAutoCancel(true)
                    .setContentIntent(pi);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(Channel_Id, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
                manager.createNotificationChannel(channel);
            }
            manager.notify(0, builder.build());
        }
        else {
            Intent intent = new Intent(this, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pi = PendingIntent.
                    getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            String Channel_Id = "Default";
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Channel_Id);
            builder.setSmallIcon(R.drawable.ic_notify)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setAutoCancel(true)
                    .setContentIntent(pi);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(Channel_Id, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
                manager.createNotificationChannel(channel);
            }
            manager.notify(0, builder.build());
        }
    }
}
