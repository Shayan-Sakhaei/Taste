package com.critics.taste.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.critics.taste.database.entity.SearchResultEntity;
import com.critics.taste.repositories.SearchRepository;

import java.util.List;

import javax.inject.Inject;

public class DetailActivityViewModel extends ViewModel {

    private LiveData<SearchResultEntity> savedResultEntity;
    private SearchRepository searchRepository;

    @Inject
    public DetailActivityViewModel(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public void init(long rowId) {
        savedResultEntity = searchRepository.getSavedResults(rowId);
    }

    public LiveData<SearchResultEntity> getSavedResultEntityLiveData() {
        return this.savedResultEntity;
    }
}
