package com.sam.imagescreening.network;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkConnection {

    Context context;

    public NetworkConnection(Context ctx) {
        this.context = ctx;
    }


    public boolean checkInternetConenction() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() ==
                android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            return true;
        } else if (
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() ==
                                android.net.NetworkInfo.State.DISCONNECTED) {

            return false;
        }
        return false;
    }
}
