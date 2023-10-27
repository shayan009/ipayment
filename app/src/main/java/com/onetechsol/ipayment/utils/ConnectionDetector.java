package com.onetechsol.ipayment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {

    private static Context _context;

    public ConnectionDetector(Context context) {
        this._context = context;
    }

    /**
     * Check Internet connection.
     *
     * @return
     */
    public static boolean isConnectingToInternet() {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
                if (isConnected || isWiFi) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}