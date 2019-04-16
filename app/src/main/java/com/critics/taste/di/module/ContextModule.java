package com.critics.taste.di.module;

import android.content.Context;

import com.critics.taste.interfaces.ApplicationContext;
import com.critics.taste.interfaces.TasteApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @ApplicationContext
    @TasteApplicationScope
    @Provides
    public Context context() {
        return context.getApplicationContext();
    }
}
