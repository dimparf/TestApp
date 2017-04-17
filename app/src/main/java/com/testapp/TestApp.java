package com.testapp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.testapp.infrastructure.AppComponent;
import com.testapp.infrastructure.AppModule;
import com.testapp.infrastructure.DaggerAppComponent;
import com.testapp.infrastructure.DaoModule;

public class TestApp extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        appComponent = buildAppComponent();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent buildAppComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .daoModule(new DaoModule())
                .build();
    }
}