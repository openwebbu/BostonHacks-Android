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

    private static final String TAG = "MyCustomReceiver";
    private int NOTIFICATION_ID = 1;
    private NotificationManager mNotifM;
    @Override
    public void onReceive(Context context, Intent intent) {
        onPushReceive(context,intent);
    }

    @Override
    public void onPushReceive(Context context, Intent intent){
        Notification notification = getNotification(context, intent);

        if (notification != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                mNotifM.notify(NOTIFICATION_ID, notification);
            }
        }
    }

    @Override
    public Notification getNotification(Context context, Intent intent) {

        String packageName = context.getPackageName();

        Intent contentIntent = new Intent(ParsePushBroadcastReceiver.ACTION_PUSH_OPEN);
        contentIntent.setPackage(packageName);

        Intent deleteIntent = new Intent(ParsePushBroadcastReceiver.ACTION_PUSH_DELETE);
        deleteIntent.setPackage(packageName);

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_announcement_white_24dp);
        mNotifM = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_announcement_white_24dp)
                        .setContentTitle("Bostonhacks")
                        .setContentText("New announcements have been posted!");
        Log.v("built new notification", "The title of the notification: " + mBuilder.mContentTitle);
        return mBuilder.build();
    }

    @Override
    public void onPushOpen(Context context, Intent intent) {
        String uriString = null;
        try {
            JSONObject pushData = new JSONObject(intent.getStringExtra(KEY_PUSH_DATA));
            uriString = pushData.optString("uri", null);
        } catch (JSONException e) {
            Log.e(TAG, "Unexpected JSONException when receiving push data: ", e);
        }

        Class<? extends Activity> cls = getActivity(context, intent);
        Intent activityIntent;
        if (uriString != null) {
            activityIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
        } else {
            activityIntent = new Intent(context, cls);
        }

        activityIntent.putExtras(intent.getExtras());
    /*
      In order to remove dependency on android-support-library-v4
      The reason why we differentiate between versions instead of just using context.startActivity
      for all devices is because in API 11 the recommended conventions for app navigation using
      the back key changed.
     */


        context.startActivity(activityIntent);

    }
}