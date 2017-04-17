package com.testapp.infrastructure;

import com.testapp.TestApp;
import com.testapp.editadscreen.EditAdPresenter;
import com.testapp.mainscreen.AdvertisementAdapter;
import com.testapp.mainscreen.AdvertisementPresenter;
import com.testapp.mainscreen.MainActivity;
import com.testapp.viewadscreen.ShowAdPresenter;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by stream on 10.06.16.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        DaoModule.class
        })
public interface AppComponent {
    void inject(TestApp TestApp);
    void inject(AdvertisementPresenter advertisementPresenter);
    void inject(MainActivity mainActivity);
    void inject(AdvertisementAdapter advertisementAdapter);
    void inject(EditAdPresenter editAdPresenter);
    void inject(ShowAdPresenter showAdPresenter);
}
