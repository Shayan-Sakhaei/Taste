package com.critics.taste.MainActivityFeature;

import com.critics.taste.MainActivity;
import com.critics.taste.adapter.SearchResultAdapter;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public SearchResultAdapter searchResultAdapter(Picasso picasso) {
        return new SearchResultAdapter(mainActivity, picasso);
    }
}
