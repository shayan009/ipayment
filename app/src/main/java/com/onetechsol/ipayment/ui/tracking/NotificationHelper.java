package com.onetechsol.ipayment.ui.tracking;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.onetechsol.ipayment.R;

public class NotificationHelper {


    public static final int NOTIFICATION_ID_FOREGROUND = 5433;

    public static String getMainChannelId(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        String channelId = context.getString(R.string.channel_tracking_id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel(channelId);
            if (channel == null) {
                String channelName = context.getString(R.string.notification_channel_tracking);
                channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
                channel.enableLights(true);
                channel.setLightColor(ContextCompat.getColor(context, R.color.colorAccent));
                channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PRIVATE);
                notificationManager.createNotificationChannel(channel);
            } else {
                channel.setImportance(NotificationManager.IMPORTANCE_DEFAULT);
                channel.enableLights(true);
                channel.setLightColor(ContextCompat.getColor(context, R.color.colorAccent));
                channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PRIVATE);
            }
        }
        return channelId;
    }
}