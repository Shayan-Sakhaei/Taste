package com.critics.taste.di.module;

import android.app.Application;

import com.critics.taste.interfaces.TasteApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }


    @TasteApplicationScope
    @Provides
    Application provideApplication() {
        return application;
    }
}
