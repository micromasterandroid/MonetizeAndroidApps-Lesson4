package com.notifications.pjcyber.firebasenotifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private String imageURL;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String message = remoteMessage.getFrom();

        Log.d("FirebaseNofifications", "Message Received: " + message);

        if (remoteMessage.getNotification().getBody() != null) {
            Log.d("FirebaseNofifications", "Notification body: " + remoteMessage.getNotification().getBody());
            showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
            imageURL = remoteMessage.getData().get("image_url");
            Log.d("FirebaseNofifications", "Notification url: " + imageURL);


        }

    }

    private void showNotification(String title, String body) {

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);

        Bitmap bitmap = getBitmapfromUrl(imageURL);

        notification.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification.build());
    }

    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
}
