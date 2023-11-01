package com.onetechsol.ipayment.ui.tracking;


import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.ActivityRecognitionClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.onetechsol.ipayment.R;
import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.data.locale.pref.PrefManager;
import com.onetechsol.ipayment.pojo.ToastItem;
import com.onetechsol.ipayment.ui.screen.home.HomeActivity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class TrackingService extends Service {


    private final static String ACTION_FUSED_LOCATION = "com.onetechsol.ipayment.ui.tracking.TrackingService.fusedLocation";
    private final static String ACTION_STOP_FOREGROUND = "com.onetechsol.ipayment.ui.tracking.TrackingService.stopforeground";
    private final static String ACTION_DETECTED_ACTIVITY = "com.onetechsol.ipayment.ui.tracking.TrackingService.detectedActivity";
    private final BroadcastReceiver mReceiver = new IntentReceiver();
    private final long DETECTION_INTERVAL = TimeUnit.SECONDS.toMillis(5);
    private final long LOCATION_INTERVAL = TimeUnit.SECONDS.toMillis(5);
    @Inject
    PrefManager prefManager;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private ActivityRecognitionClient mActivityRecognitionClient;

    public static void stopService(Context context) {
        context.sendBroadcast(new Intent(ACTION_STOP_FOREGROUND));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MainApp.appComponent().inject(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_USER_PRESENT);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(ACTION_STOP_FOREGROUND);
        filter.addAction(ACTION_FUSED_LOCATION);
        registerReceiver(mReceiver, filter);

        startForeground();
        startFusedLocation();
    }

    private void actionFusedLocation(Intent intent) {
        if (LocationResult.hasResult(intent)) {
            LocationResult locationResult = LocationResult.extractResult(intent);
            if (locationResult != null && locationResult.getLastLocation() != null) {
                //Log.d("location", String.valueOf(locationResult.getLastLocation().getLatitude()));
                //Log.d("location", String.valueOf(locationResult.getLastLocation().getLongitude()));
                ////Toast.makeText(this, "Current Location : [ " + locationResult.getLastLocation().getLatitude() + "," + locationResult.getLastLocation().getLongitude() + "]", //Toast.LENGTH_SHORT).show();

            }
        }
    }


    @Override
    public void onDestroy() {


        unregisterReceiver(mReceiver);


        stopFusedLocation();
        super.onDestroy();
        stopForeground(false);


    }

    private void actionStopForeground() {
        //Toast toast = Toast.makeText(TrackingService.this, R.string.tracking_close_alert, Toast.LENGTH_LONG);
      /*  TextView tv = toast.getView().findViewById(android.R.id.message);
        if (tv != null)
            tv.setTypeface(ResourcesCompat.getFont(TrackingService.this, R.font.montserrat_medium));
        toast.show();*/
        stopSelf();
    }

    private void actionScreenOff() {
        stopFusedLocation();
    }

    private void stopFusedLocation() {
        if (mFusedLocationProviderClient != null) {
            mFusedLocationProviderClient.removeLocationUpdates(getFusedLocationPendingIntent());
            mFusedLocationProviderClient = null;
        }
    }

    private void startFusedLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


            LocationRequest.Builder locationRequest = new LocationRequest.Builder(60000);
            locationRequest.setPriority(Priority.PRIORITY_HIGH_ACCURACY);


            mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            // mFusedLocationProviderClient.requestLocationUpdates(locationRequest.build(), getFusedLocationPendingIntent());

            mFusedLocationProviderClient.requestLocationUpdates(locationRequest.build(), location -> {

                Toast.makeText(getApplicationContext(),"Current gps location: "+String.valueOf(location.getLatitude())+"-----"+String.valueOf(location.getLongitude()),Toast.LENGTH_SHORT).show();
                prefManager.setCurrentLocation(location.getLatitude(), location.getLongitude());


                // //Toast.makeText(this, "Current Location : [ " + location.getLatitude() + "," + location.getLongitude() + "]", //Toast.LENGTH_SHORT).show();
            }, Looper.myLooper());
        }
    }

//    private void startFusedLocation() {
//
//        LocationRequest.Builder locationRequest = new LocationRequest.Builder(2000);
//        locationRequest.setPriority(Priority.PRIORITY_HIGH_ACCURACY);
//
//        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mFusedLocationProviderClient.requestLocationUpdates(locationRequest.build(), location -> {
//            Log.d("location", String.valueOf(location.getLatitude()));
//            Log.d("location", String.valueOf(location.getLongitude()));
//            //Toast.makeText(this, "Current Location : [ " + location.getLatitude() + "," + location.getLongitude() + "]", //Toast.LENGTH_SHORT).show();
//        }, Looper.myLooper());
//    }

    private PendingIntent getActivityDetectionPendingIntent() {
        Intent intent = new Intent(ACTION_DETECTED_ACTIVITY);
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
    }

    private PendingIntent getFusedLocationPendingIntent() {
        Intent intent = new Intent(ACTION_FUSED_LOCATION);
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
    }

    private void startForeground() {
        Notification notification = new NotificationCompat.Builder(this, NotificationHelper.getMainChannelId(this))
                .setSmallIcon(R.drawable.logo)

                .setContentTitle(getString(R.string.notification_tracking))
                .setContentIntent(getNotificationIntent())
                .setTicker(getText(R.string.ticker_text))
                .setColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .build();

        startForeground(NotificationHelper.NOTIFICATION_ID_FOREGROUND, notification);
    }

    private PendingIntent getNotificationIntent() {
        Intent notificationIntent = new Intent(this, HomeActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
    }

    private class IntentReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Intent.getAction : -> ", intent.getAction());
            if (!TextUtils.isEmpty(intent.getAction())) {
                switch (intent.getAction()) {
                    case ACTION_STOP_FOREGROUND: {
                        actionStopForeground();
                        break;
                    }
                    case ACTION_FUSED_LOCATION: {
                        actionFusedLocation(intent);
                        break;
                    }
                    case Intent.ACTION_USER_PRESENT:
                        startFusedLocation();
                        break;
                    case Intent.ACTION_SCREEN_OFF:
                        actionScreenOff();
                    default: {
                    }
                }

                //   Log.v("blabla", intent.getAction() + " " + mDetectedActivity.toString() + " speed=" + String.valueOf(mSpeed));
            }
        }
    }

}
