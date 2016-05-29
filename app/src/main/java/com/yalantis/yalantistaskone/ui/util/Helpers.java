package com.yalantis.yalantistaskone.ui.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.facebook.AccessToken;


/**
 * Created by Антон on 27.05.2016.
 */
public class Helpers {
    /**
     * Check if internet is available
     */
    public static boolean hasInternet(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        return i != null && i.isConnected() && i.isAvailable();
    }

    /**
     * check is facebook user is logged in
     */
    public static boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

}
