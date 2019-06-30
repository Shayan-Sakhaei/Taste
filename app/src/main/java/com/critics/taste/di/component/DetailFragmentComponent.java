package com.critics.taste.di.component;

import com.critics.taste.fragments.DetailFragment;
import com.critics.taste.interfaces.DetailFragmentScope;

import dagger.BindsInstance;
import dagger.Component;

@DetailFragmentScope
@Component(dependencies = AppComponent.class)
public interface DetailFragmentComponent {

    void injectDetailFragment(DetailFragment detailFragment);

    @Component.Builder
    interface Builder {
        DetailFragmentComponent build();

        @BindsInstance
        Builder fragment(DetailFragment detailFragment);

        Builder appComponent(AppComponent appComponent);


    }
}
