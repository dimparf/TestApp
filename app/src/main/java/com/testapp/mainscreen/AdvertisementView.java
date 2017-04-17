package com.testapp.mainscreen;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.testapp.model.Advertisement;

import io.realm.RealmResults;

/**
 * Created by stream on 17.04.17.
 */

interface AdvertisementView extends MvpView {
    void loadAds(RealmResults<Advertisement> ads);
}
