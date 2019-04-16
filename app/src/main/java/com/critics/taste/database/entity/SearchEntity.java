package com.critics.taste.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.critics.taste.database.converter.TasteDiveTypeConverters;

import java.util.List;

@Entity
public class SearchEntity {

    @NonNull
    @PrimaryKey
    private int id;

    @NonNull
    private String query;

    @TypeConverters(TasteDiveTypeConverters.class)
    @NonNull
    private List<SearchResultEntity> results;

    public SearchEntity() {
    }

    @Ignore
    public SearchEntity(int id, @NonNull String query, @NonNull List<SearchResultEntity> results) {
        this.id = id;
        this.query = query;
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getQuery() {
        return query;
    }

    public void setQuery(@NonNull String query) {
        this.query = query;
    }

    @NonNull
    public List<SearchResultEntity> getResults() {
        return results;
    }

    public void setResults(@NonNull List<SearchResultEntity> results) {
        this.results = results;
    }
}
