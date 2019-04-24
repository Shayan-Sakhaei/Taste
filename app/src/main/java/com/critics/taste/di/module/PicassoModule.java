package com.critics.taste.di.module;

import android.app.Application;
import android.content.Context;

import com.critics.taste.interfaces.ApplicationContext;
import com.critics.taste.interfaces.TasteApplicationScope;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import okhttp3.OkHttpClient;

@Module
public class PicassoModule {

    @Reusable
    @Provides
    public Picasso picasso(Application context, OkHttpClient okHttpClient) {
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
    }
}
