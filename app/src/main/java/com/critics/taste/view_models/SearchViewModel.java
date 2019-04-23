package com.critics.taste.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.critics.taste.database.entity.SearchResultEntity;
import com.critics.taste.repositories.SearchRepository;

import java.util.List;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {

    private LiveData<List<SearchResultEntity>> searchResultEntity = new MutableLiveData<>();
    private SearchRepository searchRepository;

    @Inject
    public SearchViewModel(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public void init(String searchQuery, String searchType, String searchLimit) {
        if (this.searchResultEntity != null) {
            return;
        }

        searchResultEntity = searchRepository.getSearchResult(searchQuery, searchType, searchLimit);
    }

    public LiveData<List<SearchResultEntity>> getSearchResultEntityLiveData() {
        return this.searchResultEntity;
    }
}
