package com.critics.taste.repositories;

import android.arch.lifecycle.LiveData;

import com.critics.taste.database.dao.SearchDao;
import com.critics.taste.database.entity.SearchEntity;
import com.critics.taste.interfaces.TasteDiveWebservice;

public class SearchRepository {

    private final TasteDiveWebservice tasteDiveWebservice;
    private final SearchDao searchDao;

    public SearchRepository(TasteDiveWebservice tasteDiveWebservice,
                            SearchDao searchDao) {
        this.tasteDiveWebservice = tasteDiveWebservice;
        this.searchDao = searchDao;
    }

    public LiveData<SearchEntity> getSearchResult(String searchQuery) {
        return searchDao.load(searchQuery);
    }
}
