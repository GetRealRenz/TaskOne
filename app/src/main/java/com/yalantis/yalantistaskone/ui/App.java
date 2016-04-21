package com.yalantis.yalantistaskone.ui;

import android.app.Application;
import android.content.Context;

import com.yalantis.yalantistaskone.ui.util.InitMock;

/**
 * Created by Антон on 18.04.2016.
 */
public class App extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }


    public static Context getContext() {
        return sContext;
    }
}
