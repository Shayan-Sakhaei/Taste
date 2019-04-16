package com.critics.taste.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.critics.taste.database.entity.SearchEntity;
import com.critics.taste.repositories.SearchRepository;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {

    private LiveData<SearchEntity> searchEntityLiveData;
    private SearchRepository searchRepository;

    @Inject
    public SearchViewModel(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public void init(String searchQuery) {
        if (this.searchEntityLiveData != null) {
            return;
        }

        searchEntityLiveData = searchRepository.getSearchResult(searchQuery);
    }

    public LiveData<SearchEntity> getSimilarLiveData() {
        return this.searchEntityLiveData;
    }
}
