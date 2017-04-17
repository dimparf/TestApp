package com.testapp.dao;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by stream on 31.05.16.
 */
class Dao {
    protected Realm realm;

    Dao(Context context) {
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder(context)
                .deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);
    }

    public Realm getRealm() {
        return realm;
    }

}