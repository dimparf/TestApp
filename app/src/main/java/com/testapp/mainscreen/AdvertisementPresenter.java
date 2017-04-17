package com.testapp.mainscreen;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.testapp.TestApp;
import com.testapp.dao.AdvertisementDao;
import com.testapp.model.Advertisement;

import javax.inject.Inject;

import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by stream on 17.04.17.
 */

public class AdvertisementPresenter extends MvpBasePresenter<AdvertisementView> {
    @Inject
    AdvertisementDao advertisementDao;

    void loadAds() {
        RealmResults<Advertisement> result = advertisementDao.findAll().sort("createdAt", Sort.DESCENDING);
        if (isViewAttached()) {
            getView().loadAds(result);
        }
    }

    @Override
    public void attachView(AdvertisementView view) {
        TestApp.getAppComponent().inject(this);
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
