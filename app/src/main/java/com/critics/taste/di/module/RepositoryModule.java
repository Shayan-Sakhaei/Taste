package com.critics.taste.di.module;

import com.critics.taste.database.dao.SearchDaoJava;
import com.critics.taste.interfaces.TasteDiveWebservice;
import com.critics.taste.repositories.SearchRepository;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class RepositoryModule {

    @Provides
    static SearchRepository provideSearchResultRepository(TasteDiveWebservice tasteDiveWebservice,
                                                          SearchDaoJava searchDaoKotlin, Executor executor) {
        return new SearchRepository(tasteDiveWebservice, searchDaoKotlin, executor);
    }
}
