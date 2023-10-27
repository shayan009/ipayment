package com.onetechsol.ipayment.boot;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import com.onetechsol.ipayment.ui.tracking.TrackingService;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == null)
            return;

        switch (intent.getAction()) {
            case Intent.ACTION_REBOOT:
            case Intent.ACTION_BOOT_COMPLETED:
                // may a sense use this on N+ devices?
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    ContextCompat.startForegroundService(context, new Intent(context, TrackingService.class));
                break;
        }
    }
}
