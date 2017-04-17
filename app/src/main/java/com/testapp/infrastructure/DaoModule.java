package com.testapp.infrastructure;

import android.content.Context;

import com.testapp.dao.AdvertisementDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by stream on 14.06.16.
 */
@Module
public class DaoModule {

    @Provides
    @Singleton
    AdvertisementDao provideAdvertisementDao(Context context) {
        return new AdvertisementDao(context);
    }
}
