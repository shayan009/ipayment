package com.onetechsol.ipayment.widgets;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.view.View;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.onetechsol.ipayment.R;


public class NotificationUtils extends ContextWrapper {
    private Context context;
    private NotificationManager mManager;

    public NotificationUtils(Context context) {
        super(context);
        this.context = context;
    }

    public void showCustomBothContentViewNotification(int chId, String title, String desc, String subtext, PendingIntent pendingIntent) {

        RemoteViews remoteViews = createRemoteViews(R.layout.notification_custom_content, "", title, desc);
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder builder = createCustomNotificationBuilder(chId, subtext);
            builder.setCustomContentView(remoteViews);
            remoteViews.setViewVisibility(R.id.img_logo_down, View.GONE);

            builder.setStyle(new Notification.DecoratedCustomViewStyle());
            if (pendingIntent != null)
                builder.setContentIntent(pendingIntent);
            notification = builder.build();

        } else {
            NotificationCompat.Builder builder = createCustomNotificationBuilderV25Less(chId, subtext);

            builder.setCustomContentView(remoteViews);
            remoteViews.setViewVisibility(R.id.img_logo_down, View.GONE);

            builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
            if (pendingIntent != null)
                builder.setContentIntent(pendingIntent);
            notification = builder.build();
        }

        showNotify(notification, chId);

    }

    private void showNotify(Notification notification, int id) {
        NotificationManager mNotificationManager = getManager();
        createChannel(mNotificationManager, id);
        mNotificationManager.notify(id, notification);
    }

    private void createChannel(NotificationManager notificationManager, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "ch1";
            String description = "desc1";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(String.valueOf(id), name, importance);
            channel.setDescription(description);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setShowBadge(true);
            channel.setBypassDnd(true);
            channel.setLightColor(context.getColor(R.color.color_green));
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(channel);
        }
    }


    public void showStandardHeadsUpNotification(int chId, String title, String desc, PendingIntent pendingIntent) {
        NotificationCompat.Builder notificationBuider = new NotificationCompat.Builder(context, String.valueOf(chId))
                .setSmallIcon(R.drawable.logo)
                .setColor(getResources().getColor(R.color.colorPrimary_Deep))
                .setShowWhen(true)
                .setAutoCancel(true)
                .setGroupSummary(true)
                .setGroup("GROUP_" + chId)
                .setContentTitle(title)
                .setContentText(desc)
                .setPriority(Notification.PRIORITY_HIGH).setVibrate(new long[0]);
        if (pendingIntent != null)
            notificationBuider.setContentIntent(pendingIntent);
        showNotify(notificationBuider.build(), chId);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder createCustomNotificationBuilder(int id, String subText) {
        return new Notification.Builder(context, String.valueOf(id))
                .setCategory("icon")
                .setShowWhen(true)
                .setSmallIcon(R.drawable.logo)
                .setColor(getResources().getColor(R.color.color_green))
                .setColorized(true)
                .setSubText(subText)
                .setAutoCancel(true);
    }

    public NotificationCompat.Builder createCustomNotificationBuilderV25Less(int id, String subText) {

        return new NotificationCompat.Builder(context, subText)
                .setCategory("icon")
                .setSmallIcon(R.drawable.logo)
                .setAutoCancel(true);
    }

    private RemoteViews createRemoteViews(int layout, String url, String title, String message) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), layout);
        remoteViews.setTextViewText(R.id.text_title, title);
        remoteViews.setTextViewText(R.id.text_message, message);
        return remoteViews;
    }

    private NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public void clearNotifications() {
        mManager.cancelAll();
    }
}