package com.critics.taste.di;

import android.content.Context;

import com.critics.taste.di.component.AppComponent;
import com.critics.taste.interfaces.AppComponentProvider;

public class AppComponentHelper {

    private AppComponentHelper() {
    }

    public static AppComponent getAppComponent(Context context) {
        Context app = context.getApplicationContext();
        if (app instanceof AppComponentProvider) {
            return ((AppComponentProvider) app).getAppcomponent();
        } else {
            throw new RuntimeException(
                    "application clas must implement AppComponentProvider");
        }
    }
}
