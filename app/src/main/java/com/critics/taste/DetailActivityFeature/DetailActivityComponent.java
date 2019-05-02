package com.critics.taste.DetailActivityFeature;

import com.critics.taste.DetailActivity;
import com.critics.taste.di.component.AppComponent;

import dagger.BindsInstance;
import dagger.Component;

@DetailActivityScope
@Component(dependencies = AppComponent.class)
public interface DetailActivityComponent {

    void injectDetailActivity(DetailActivity detailActivity);

    @Component.Builder
    interface Builder {

        DetailActivityComponent build();

        @BindsInstance
        Builder activity(DetailActivity detailActivity);

        Builder appcomponent(AppComponent appComponent);
    }
}
