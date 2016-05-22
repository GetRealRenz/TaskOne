package com.yalantis.yalantistaskone.ui.model;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/**
 * Created by Антон on 20.05.2016.
 */
public class Migration implements RealmMigration {
    public static final long CURRENT_VERSION = 0;


    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

    }
}
