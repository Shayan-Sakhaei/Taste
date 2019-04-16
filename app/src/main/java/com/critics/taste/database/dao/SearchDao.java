package com.critics.taste.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.critics.taste.database.entity.SearchEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface SearchDao {

    @Insert(onConflict = REPLACE)
    void save(SearchEntity searchEntity);

    @Query("SELECT * FROM searchentity WHERE `query` = :searchQuery")
    LiveData<SearchEntity> load(String searchQuery);
}
