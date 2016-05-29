package com.yalantis.yalantistaskone.ui;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.yalantis.yalantistaskone.ui.manager.ApiManager;
import com.yalantis.yalantistaskone.ui.manager.DataManager;
import com.yalantis.yalantistaskone.ui.model.Migration;
import com.yalantis.yalantistaskone.ui.util.Constants;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by Антон on 18.04.2016.
 */
public class App extends Application {
    private static Context sContext;
    private static DataManager sDataManager;
    private static ApiManager sApiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        setupRealmDefaultInstance();
        FacebookSdk.sdkInitialize(getContext());
    }

    public static DataManager getDataManager() {
        if (sDataManager == null) {
            sDataManager = new DataManager();
            sDataManager.init(getContext());
        }
        return sDataManager;
    }

    public static ApiManager getApiManager() {
        if (sApiManager == null) {
            sApiManager = new ApiManager();
            sApiManager.init(getContext());
        }
        return sApiManager;
    }


    public void clear() {
        sDataManager = null;
        sApiManager = null;
    }


    public static Context getContext() {
        return sContext;
    }

    private static void setupRealmDefaultInstance() {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(sContext)
                .name(Constants.STORAGE_MAIN)
                .schemaVersion(Migration.CURRENT_VERSION)
                .migration(new Migration())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
