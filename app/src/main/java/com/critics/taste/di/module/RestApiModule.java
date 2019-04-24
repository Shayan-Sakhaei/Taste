package com.critics.taste.di.module;

import com.critics.taste.interfaces.TasteDiveWebservice;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class RestApiModule {

    @Provides
    public static TasteDiveWebservice tasteDiveWebservice(Retrofit retrofit) {
        return retrofit.create(TasteDiveWebservice.class);
    }

    @Reusable
    @Provides
    public static Retrofit retrofit(OkHttpClient okHttpClient,
                             Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://tastedive.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    public static Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }
}
