package com.critics.taste.MainActivityFeature;

import com.critics.taste.MainActivity;
import com.critics.taste.di.component.AppComponent;

import dagger.Component;

@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = AppComponent.class)
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);
}
