package com.critics.taste.di.module;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.critics.taste.interfaces.ApplicationContext;
import com.critics.taste.interfaces.TasteApplicationScope;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.critics.taste.BuildConfig.DEBUG;

@Module
public abstract class OkHttpClientModule {

    @Singleton
    @Provides
    public static OkHttpClient okHttpClient(Cache cache, HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient
                .Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    public static Cache cache(@Named("rest_cache_file") File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000); //10 MB
    }

    @Named("rest_cache_file")
    @Provides
    public static File file(Application context) {
        File file = new File(context.getCacheDir(), "HttpCache");
        file.mkdirs();
        return file;
    }

    @Provides
    public static HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(
                message -> Log.d("OkHttpClientModule", message));
        httpLoggingInterceptor.setLevel(DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return httpLoggingInterceptor;
    }
}
