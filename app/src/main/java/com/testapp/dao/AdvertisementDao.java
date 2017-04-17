package com.testapp.dao;

import android.content.Context;
import android.util.Log;

import com.testapp.model.Advertisement;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by stream on 17.04.17.
 */

public class AdvertisementDao extends Dao {
    private static final String TAG = AdvertisementDao.class.getSimpleName();

    public AdvertisementDao(Context context) {
        super(context);
    }

    public void updateOrCreate(final Advertisement inAd) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Advertisement inDbAd = realm.where(Advertisement.class).equalTo("title", inAd.title).findFirst();
                if (inDbAd != null) {
                    Log.d(TAG, "Update ad with title: " + inDbAd.title);
                    realm.copyToRealmOrUpdate(inAd);
                } else {
                    Log.d(TAG, "Create new ad with title: " + inAd.title);
                    realm.copyToRealm(inAd);
                }
            }
        });
    }

    public Advertisement findByTitle(String title) {
        return realm.where(Advertisement.class)
                .equalTo("title", title)
                .findFirst();
    }

    public RealmResults<Advertisement> findAll() {
        return realm.where(Advertisement.class).findAll();
    }

    public void deleteByTitle(final String title) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Advertisement advertisement = realm.where(Advertisement.class)
                        .equalTo("title", title)
                        .findFirst();
                if (advertisement != null) {
                    advertisement.deleteFromRealm();
                }
            }
        });
    }

}
