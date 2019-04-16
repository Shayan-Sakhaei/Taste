package com.critics.taste.application;

import android.app.Activity;
import android.app.Application;

import com.critics.taste.di.component.AppComponent;
import com.critics.taste.di.component.DaggerAppComponent;
import com.critics.taste.di.module.ContextModule;

public class TasteApplication extends Application {

    private AppComponent appComponent;

    public static TasteApplication get(Activity activity) {
        return (TasteApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
