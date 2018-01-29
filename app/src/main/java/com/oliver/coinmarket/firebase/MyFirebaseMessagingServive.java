package com.oliver.coinmarket.firebase;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Oliver on 1/29/2018.
 */

public class MyFirebaseMessagingServive extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String notificationText = "AAAEEEUUU";

        String notificationBody = "";
        if (remoteMessage.getNotification()!=null)
            notificationBody = remoteMessage.getNotification().getBody();

        Intent intent = new Intent("android.intent.action.FIREBASENOTIF").
                putExtra("notification",notificationText).
                putExtra("notificationBody",notificationBody);
        this.sendBroadcast(intent);
        Log.d("remotemessage",notificationBody);

    }
}
