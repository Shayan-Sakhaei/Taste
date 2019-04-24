package com.critics.taste.application;

import android.app.Application;

import com.critics.taste.di.component.AppComponent;
import com.critics.taste.di.component.DaggerAppComponent;
import com.critics.taste.interfaces.AppComponentProvider;

public class TasteApplication extends Application implements AppComponentProvider {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .bindApplication(this)
                .build();
    }

    @Override
    public AppComponent getAppcomponent() {
        return appComponent;
    }
}
