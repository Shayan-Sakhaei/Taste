package com.critics.taste.di.component;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import com.critics.taste.database.dao.SearchDao;
import com.critics.taste.di.module.DatabaseModule;
import com.critics.taste.di.module.OkHttpClientModule;
import com.critics.taste.di.module.PicassoModule;
import com.critics.taste.di.module.RepositoryModule;
import com.critics.taste.di.module.RestApiModule;
import com.critics.taste.di.module.ViewModelModule;
import com.critics.taste.interfaces.TasteApplicationScope;
import com.critics.taste.interfaces.TasteDiveWebservice;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@TasteApplicationScope
@Component(modules = {
        OkHttpClientModule.class,
        RestApiModule.class,
        PicassoModule.class,
        DatabaseModule.class,
        RepositoryModule.class,
        ViewModelModule.class})
public interface AppComponent {

    TasteDiveWebservice getTasteDiveWebservice();

    Picasso getPicasso();

    SearchDao getSearchDao();

    Executor getExecutor();

    ViewModelProvider.Factory getAppComponent();

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder bindApplication(Application app);

        AppComponent build();
    }
}
