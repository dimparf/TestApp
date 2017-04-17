package com.testapp.viewadscreen;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.testapp.model.Advertisement;

/**
 * Created by stream on 17.04.17.
 */

public interface ShowAdView extends MvpView {
    void showAd(Advertisement advertisement);
}
