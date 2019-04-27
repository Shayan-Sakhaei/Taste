package com.critics.taste.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.critics.taste.database.dao.SearchDao;
import com.critics.taste.database.entity.Api;
import com.critics.taste.database.entity.Result;
import com.critics.taste.database.entity.SearchResultEntity;
import com.critics.taste.database.entity.Similar;
import com.critics.taste.interfaces.TasteDiveWebservice;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {

    private final TasteDiveWebservice tasteDiveWebservice;
    private final SearchDao searchDao;
    private final Executor executor;

    @Inject
    public SearchRepository(TasteDiveWebservice tasteDiveWebservice,
                            SearchDao searchDao,
                            Executor executor) {
        this.tasteDiveWebservice = tasteDiveWebservice;
        this.searchDao = searchDao;
        this.executor = executor;
    }

    public LiveData<List<SearchResultEntity>> getSearchResult(
            String searchQuery,
            String searchType,
            String searchLimit) {
        refreshResults(searchQuery, searchType, searchLimit);
        if (searchType.equals("mixed")) {
            return searchDao.loadMixed(searchQuery, searchLimit);
        } else {
            return searchDao.load(searchQuery, searchType, searchLimit);
        }
    }

    private void refreshResults(final String userSearchQuery
            , String userSearchType, String userSearchLimit) {
        executor.execute(() -> {
            boolean resultExists = (searchDao.hasResult(userSearchQuery) != null);
//            if (!resultExists) {
            Call<Api> call;

            if (userSearchType.equals("mixed")) {
                call = tasteDiveWebservice.getApiByMixedAndInfo(
                        "333837-TasteMyT-HBUN5GWG", userSearchQuery
                        , userSearchLimit);
            } else {
                call = tasteDiveWebservice.getApiByTypeAndInfo(
                        "333837-TasteMyT-HBUN5GWG", userSearchQuery
                        , userSearchType, userSearchLimit);
            }
            call.enqueue(new Callback<Api>() {
                @Override
                public void onResponse(Call<Api> call, Response<Api> response) {
                    executor.execute(() -> {
                        Similar similar = response.body().getSimilar();
                        List<Result> results = similar.getResults();
                        for (Result result : results) {
                            SearchResultEntity searchResultEntity =
                                    new SearchResultEntity(userSearchQuery,
                                            result.getName(), result.getType(),
                                            result.getWTeaser(), result.getWUrl(),
                                            result.getYUrl(), result.getYID());

                            searchDao.save(searchResultEntity);
                        }

                    });
                }

                @Override
                public void onFailure(Call<Api> call, Throwable t) {

                }
            });
//            }
        });


    }
}
