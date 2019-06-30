package com.critics.taste.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.critics.taste.database.entity.SearchResultEntity;
import com.critics.taste.repositories.SearchRepository;
import com.critics.taste.work_manager.TasteWorker;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

public class MainActivityViewModel extends ViewModel {

    private LiveData<List<SearchResultEntity>> searchResultEntity;
    private LiveData<SearchResultEntity> savedResultEntity;
    private SearchRepository searchRepository;
    private WorkManager workManager;

    @Inject
    public MainActivityViewModel(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
        workManager = WorkManager.getInstance();
    }

    private final MutableLiveData<SearchResultEntity> selected = new MutableLiveData<SearchResultEntity>();

    public void select(SearchResultEntity searchResultEntity) {
        selected.setValue(searchResultEntity);
    }

    public LiveData<SearchResultEntity> getSelected() {
        return selected;
    }





    public void initSearchApi(String searchQuery, String searchType, String searchLimit) {
        searchResultEntity = searchRepository.getSearchResult(searchQuery, searchType, searchLimit);
    }

    public LiveData<List<SearchResultEntity>> getSearchResultList() {
        return this.searchResultEntity;
    }

    public void initSearchDb(long rowId) {
        savedResultEntity = searchRepository.getSavedResults(rowId);
    }

    public LiveData<SearchResultEntity> getSavedResult() {
        return this.savedResultEntity;
    }

    public void deleteSavedResult(long rowId) {
        searchRepository.deleteSavedResult(rowId);
    }


    public void sendNotification() {
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();

        WorkRequest request = new OneTimeWorkRequest.Builder(TasteWorker.class)
                .setConstraints(constraints).build();

        workManager.enqueue(request);
    }
}
