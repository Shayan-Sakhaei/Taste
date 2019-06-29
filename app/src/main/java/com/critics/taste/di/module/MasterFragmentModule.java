package com.critics.taste.di.module;

import com.critics.taste.adapter.SearchResultAdapter;
import com.critics.taste.interfaces.MasterFragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class MasterFragmentModule {
    @Provides
    @MasterFragmentScope
    public static SearchResultAdapter searchResultAdapter() {
        return new SearchResultAdapter();
    }
}
