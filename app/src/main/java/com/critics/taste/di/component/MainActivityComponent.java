package com.critics.taste.di.component;

import com.critics.taste.MainActivity;
import com.critics.taste.di.module.MainActivityModule;
import com.critics.taste.interfaces.MainActivityScope;

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

        Builder appComponent(AppComponent appComponent);
    }
}
