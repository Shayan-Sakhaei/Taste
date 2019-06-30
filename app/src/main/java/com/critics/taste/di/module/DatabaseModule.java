package com.critics.taste.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.critics.taste.database.MyDatabase;
import com.critics.taste.database.dao.SearchDaoJava;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class DatabaseModule {

    @Singleton
    @Provides
    static MyDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                MyDatabase.class, "Taste.db")
                .build();
    }

    @Provides
    static SearchDaoJava provideSearchDao(MyDatabase myDatabase) {
        return myDatabase.searchDao();
    }

    @Provides
    static Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
