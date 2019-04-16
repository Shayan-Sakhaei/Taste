package com.critics.taste.di.module;

import android.app.Activity;
import android.content.Context;

import com.critics.taste.interfaces.TasteApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Context context;

    ActivityModule(Activity context) {
        this.context = context;
    }

    @TasteApplicationScope
    @Provides
    public Context context() {
        return context;
    }
}
