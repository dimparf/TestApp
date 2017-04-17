package com.testapp.editadscreen;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.testapp.TestApp;
import com.testapp.dao.AdvertisementDao;
import com.testapp.model.Advertisement;

import java.util.Date;

import javax.inject.Inject;

/**
 * Created by stream on 17.04.17.
 */

public class EditAdPresenter extends MvpBasePresenter<EditAdView> {

    @Inject
    AdvertisementDao advertisementDao;

    void saveAd(String title, String price, String content) {
        Advertisement advertisement = new Advertisement();
        try {
            advertisement.price = Double.parseDouble(price);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            getView().showPriceWarning();
        }
        long createdAt = new Date().getTime();
        if (title != null && !title.trim().isEmpty()) {
            advertisement.title = title;
            advertisement.content = content;
            advertisement.createdAt = createdAt;
            advertisementDao.updateOrCreate(advertisement);
            getView().finish();
        } else {
            getView().showAdsTitleWarning();
        }

    }

    @Override
    public void attachView(EditAdView view) {
        TestApp.getAppComponent().inject(this);
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }

    void loadAd(String adTitle) {
        Advertisement advertisement = advertisementDao.findByTitle(adTitle);
        if (advertisement != null & isViewAttached()) {
            getView().loadAd(advertisement);
        }
    }

    void deleteAd(String title) {
        advertisementDao.deleteByTitle(title);
    }
}
