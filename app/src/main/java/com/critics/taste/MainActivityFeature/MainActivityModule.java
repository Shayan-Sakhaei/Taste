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
public class MainActivityModule {

//    private final MainActivity mainActivity;
//
//    public MainActivityModule(MainActivity mainActivity) {
//        this.mainActivity = mainActivity;
//    }

    @Provides
    @MainActivityScope
    public SearchResultAdapter searchResultAdapter() {
        return new SearchResultAdapter();
    }

    @Provides
    @MainActivityScope
    public SearchRepository searchRepository(TasteDiveWebservice tasteDiveWebservice
            , SearchDao searchDao, Executor executor) {
        return new SearchRepository(tasteDiveWebservice, searchDao, executor);
    }
}
