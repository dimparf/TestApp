package com.testapp.viewadscreen;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.testapp.TestApp;
import com.testapp.dao.AdvertisementDao;
import com.testapp.model.Advertisement;

import javax.inject.Inject;

/**
 * Created by stream on 17.04.17.
 */

public class ShowAdPresenter extends MvpBasePresenter<ShowAdView> {

    @Inject
    AdvertisementDao advertisementDao;
    @Override
    public void attachView(ShowAdView view) {
        TestApp.getAppComponent().inject(this);
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }

    public void loadAd(String adTitle) {
        Advertisement advertisement = advertisementDao.findByTitle(adTitle);
        if (advertisement != null && isViewAttached()) {
            getView().showAd(advertisement);
        }
    }
}
