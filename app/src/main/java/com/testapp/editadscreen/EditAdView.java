package com.testapp.editadscreen;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.testapp.model.Advertisement;

/**
 * Created by stream on 17.04.17.
 */

public interface EditAdView extends MvpView {
    void loadAd(Advertisement advertisement);

    void showPriceWarning();

    void finish();

    void showAdsTitleWarning();
}
