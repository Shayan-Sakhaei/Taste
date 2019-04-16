package com.critics.taste.di.module;

import com.critics.taste.interfaces.TasteApplicationScope;
import com.critics.taste.interfaces.TasteDiveWebservice;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OkHttpClientModule.class)
public class RestApiModule {

    @Provides
    public TasteDiveWebservice tasteDiveWebservice(Retrofit retrofit) {
        return retrofit.create(TasteDiveWebservice.class);
    }

    @TasteApplicationScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient,
                             GsonConverterFactory gsonConverterFactory,
                             Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://tastedive.com")
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }
}
