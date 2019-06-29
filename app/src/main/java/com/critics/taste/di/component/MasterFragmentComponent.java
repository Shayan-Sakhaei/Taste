package com.critics.taste.di.component;

import com.critics.taste.di.module.MasterFragmentModule;
import com.critics.taste.fragments.MasterFragment;
import com.critics.taste.interfaces.MasterFragmentScope;

import dagger.BindsInstance;
import dagger.Component;

@MasterFragmentScope
@Component(modules = MasterFragmentModule.class, dependencies = AppComponent.class)
public interface MasterFragmentComponent {

    void injectMasterFragment(MasterFragment masterFragment);

    @Component.Builder
    interface Builder {
        MasterFragmentComponent build();

        @BindsInstance
        Builder fragment(MasterFragment masterFragment);

        Builder appComponent(AppComponent appComponent);

    }
}
