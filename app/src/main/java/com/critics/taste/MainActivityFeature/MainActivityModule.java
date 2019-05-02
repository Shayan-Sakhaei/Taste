package com.critics.taste.MainActivityFeature;

import android.arch.persistence.room.PrimaryKey;

import com.critics.taste.MainActivity;
import com.critics.taste.adapter.SearchResultAdapter;
import com.critics.taste.database.dao.SearchDao;
import com.critics.taste.interfaces.TasteDiveWebservice;
import com.critics.taste.repositories.SearchRepository;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainActivityModule {

    @Provides
    @MainActivityScope
    public static SearchResultAdapter searchResultAdapter() {
        return new SearchResultAdapter();
    }
}
