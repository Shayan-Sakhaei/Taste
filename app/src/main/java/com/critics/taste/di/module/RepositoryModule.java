package com.critics.taste.di.module;

import com.critics.taste.database.dao.SearchDao;
import com.critics.taste.interfaces.TasteDiveWebservice;
import com.critics.taste.repositories.SearchRepository;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class RepositoryModule {


    //is it really needed to be provided using module and @Provides? it already has a @Inject constructor
    @Provides
    static SearchRepository provideSearchResultRepository(
            TasteDiveWebservice tasteDiveWebservice,
            SearchDao searchDao,
            Executor executor) {
        return new SearchRepository(tasteDiveWebservice, searchDao, executor);
    }
}
