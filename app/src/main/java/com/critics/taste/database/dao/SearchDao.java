package com.critics.taste.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.critics.taste.database.entity.SearchResultEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface SearchDao {

    @Insert(onConflict = REPLACE)
    void save(SearchResultEntity searchResultEntity);

    @Query("SELECT * FROM results_table WHERE search_query = :searchQuery")
    LiveData<List<SearchResultEntity>> load(String searchQuery);

    @Query("SELECT * FROM results_table WHERE search_query = :userQuery")
    SearchResultEntity hasResult(String userQuery);
}
