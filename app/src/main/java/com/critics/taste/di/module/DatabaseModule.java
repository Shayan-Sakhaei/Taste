package com.critics.taste.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.critics.taste.database.MyDatabase;
import com.critics.taste.database.dao.SearchDao;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;

@Module(includes = {AppModule.class, ViewModelModule.class})
public class DatabaseModule {

    @Provides
    MyDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                MyDatabase.class, "Taste.db")
                .build();
    }

    @Provides
    SearchDao provideSearchDao(MyDatabase myDatabase) {
        return myDatabase.searchDao();
    }

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
