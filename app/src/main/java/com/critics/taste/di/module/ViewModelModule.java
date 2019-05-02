package com.critics.taste.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.critics.taste.di.key.ViewModelKey;
import com.critics.taste.view_models.DetailActivityViewModel;
import com.critics.taste.view_models.FactoryViewModel;
import com.critics.taste.view_models.MainActivityViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    abstract ViewModel bindMainActivityViewModel(MainActivityViewModel mainActivityViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailActivityViewModel.class)
    abstract ViewModel bindDetailActivityViewModel(DetailActivityViewModel detailActivityViewModel);


}
