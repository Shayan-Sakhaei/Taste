package com.critics.taste.MainActivityFeature;

import com.critics.taste.MainActivity;
import com.critics.taste.di.component.AppComponent;
import com.critics.taste.di.module.ViewModelModule;

import dagger.BindsInstance;
import dagger.Component;

@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = AppComponent.class)
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);

    @Component.Builder
    interface Builder {

        MainActivityComponent build();

        @BindsInstance
        Builder activity(MainActivity mainActivity);

        Builder appcomponent(AppComponent appComponent);
    }
}
