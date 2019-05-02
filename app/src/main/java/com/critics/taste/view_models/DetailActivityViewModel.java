package com.critics.taste.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.critics.taste.database.entity.SearchResultEntity;
import com.critics.taste.repositories.SearchRepository;

import java.util.List;

import javax.inject.Inject;

public class DetailActivityViewModel extends ViewModel {

    private LiveData<List<SearchResultEntity>> savedResultEntity;
    private SearchRepository searchRepository;

    @Inject
    public DetailActivityViewModel(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public void init(String searchQuery, String searchType, String searchLimit) {
        savedResultEntity = searchRepository.getSavedResults(searchQuery, searchType, searchLimit);
    }

    public LiveData<List<SearchResultEntity>> getSavedResultEntityLiveData() {
        return this.savedResultEntity;
    }
}
