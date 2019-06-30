package com.critics.taste.di.module;

import com.critics.taste.adapter.SearchResultAdapter;
import com.critics.taste.interfaces.MainActivityScope;

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
