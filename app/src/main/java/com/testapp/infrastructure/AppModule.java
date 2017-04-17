package com.testapp.infrastructure;

import android.content.Context;

import com.testapp.TestApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by stream on 10.06.16.
 */
@Module
public class AppModule {
    private TestApp application;

    public AppModule(TestApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }
}
