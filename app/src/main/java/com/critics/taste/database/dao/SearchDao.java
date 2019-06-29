package com.critics.taste.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.critics.taste.database.entity.SearchResultEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface SearchDao {

    @Insert(onConflict = REPLACE)
    void save(SearchResultEntity searchResultEntity);

    @Delete
    void delete(SearchResultEntity searchResultEntity);

    @Query("SELECT * FROM results_table WHERE search_query = :searchQuery AND" +
            " result_type = :searchType ORDER BY result_name ASC LIMIT :searchLimit")
    LiveData<List<SearchResultEntity>> load(String searchQuery, String searchType, String searchLimit);

    @Query("SELECT * FROM results_table WHERE search_query = :searchQuery " +
            "ORDER BY result_name ASC LIMIT :searchLimit")
    LiveData<List<SearchResultEntity>> loadMixed(String searchQuery, String searchLimit);

    @Query("SELECT * FROM results_table WHERE row_id = :rowId")
    LiveData<SearchResultEntity> loadSavedResult(long rowId);


}
