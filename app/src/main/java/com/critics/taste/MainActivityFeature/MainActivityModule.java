package com.critics.taste.MainActivityFeature;

import com.critics.taste.adapter.SearchResultAdapter;
import com.critics.taste.database.dao.SearchDao;
import com.critics.taste.interfaces.TasteDiveWebservice;
import com.critics.taste.repositories.SearchRepository;

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

    //it's a duplicate provider
//    @Provides
//    @MainActivityScope
//    public static SearchRepository searchRepository(
//            TasteDiveWebservice tasteDiveWebservice,
//            SearchDao searchDao,
//            Executor executor) {
//        return new SearchRepository(tasteDiveWebservice, searchDao, executor);
//    }
}
