package com.critics.taste.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.critics.taste.database.converter.DateConverter;
import com.critics.taste.database.dao.SearchDao;
import com.critics.taste.database.entity.SearchEntity;

@Database(entities = {SearchEntity.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class MyDatabase extends RoomDatabase {

    //SINGLETON
    private static volatile MyDatabase INSTANCE;

    //DAO
    public abstract SearchDao searchDao();
}
