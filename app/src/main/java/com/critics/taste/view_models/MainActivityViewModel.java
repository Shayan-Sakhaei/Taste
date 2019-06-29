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
    private SearchRepository searchRepository;
    private WorkManager workManager;

    @Inject
    public MainActivityViewModel(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
        workManager = WorkManager.getInstance();
    }

    public void init(String searchQuery, String searchType, String searchLimit) {
//        if (this.searchResultEntity != null) {
//            return;
//        }

        searchResultEntity = searchRepository.getSearchResult(searchQuery, searchType, searchLimit);
    }

    public LiveData<List<SearchResultEntity>> getSearchResultEntityLiveData() {
        return this.searchResultEntity;
    }

    public void delete(SearchResultEntity searchResultEntity) {
        searchRepository.delete(searchResultEntity);
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
