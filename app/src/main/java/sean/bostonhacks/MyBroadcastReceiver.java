package sean.bostonhacks;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;


import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;
import java.util.Random;


/**
 * Created by Sean on 10/30/15.
 */
public class MyBroadcastReceiver extends ParsePushBroadcastReceiver {

    private final String TAG = "PUSH_NOTIF";
    public int numMessages = 0;

    @Override
    public void onPushOpen(Context context, Intent intent) {
        Log.i(TAG, "onPushOpen triggered!");
        Intent i = new Intent(context, MainActivity.class);
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    @Override
    public void onPushReceive(Context context, Intent intent) {
//        Log.i(TAG, "onPushReceive triggered!");

        JSONObject pushData;
        String alert = null;
        String title = null;
        try {
            pushData = new JSONObject(intent.getStringExtra(MyBroadcastReceiver.KEY_PUSH_DATA));
            alert = pushData.getString("alert");
            title = pushData.getString("title");
        } catch (JSONException e) {}

//        Log.i(TAG,"alert is " + alert);
//        Log.i(TAG,"title is " + title);

        Intent cIntent = new Intent(MyBroadcastReceiver.ACTION_PUSH_OPEN);
        cIntent.putExtras(intent.getExtras());
        cIntent.setPackage(context.getPackageName());

        PendingIntent pContentIntent =
                PendingIntent.getBroadcast(context, 0 /*just for testing*/, cIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder
                .setSmallIcon(R.drawable.ic_announcement_white_24dp)
                .setContentTitle("New announcement")
                .setContentText(alert)
                .setContentIntent(pContentIntent)
                .setAutoCancel(true)
                .setNumber(numMessages);


        NotificationManager myNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        myNotificationManager.notify(1, builder.build());
    }
}