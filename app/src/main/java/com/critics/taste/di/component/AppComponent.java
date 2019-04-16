package com.critics.taste.di.component;

import com.critics.taste.di.module.DatabaseModule;
import com.critics.taste.di.module.PicassoModule;
import com.critics.taste.di.module.RepositoryModule;
import com.critics.taste.di.module.RestApiModule;
import com.critics.taste.interfaces.TasteApplicationScope;
import com.critics.taste.interfaces.TasteDiveWebservice;
import com.squareup.picasso.Picasso;

import dagger.Component;

@TasteApplicationScope
@Component(modules = {RestApiModule.class, PicassoModule.class, DatabaseModule.class, RepositoryModule.class})
public interface AppComponent {

    TasteDiveWebservice getTasteDiveWebservice();

    Picasso getPicasso();
}
