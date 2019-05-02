package com.critics.taste.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import dagger.Provides;

@Entity(tableName = "results_table")
public class SearchResultEntity {

    @NonNull
    @ColumnInfo(name = "search_query")
    private String query;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "result_name")
    private String name;

    @NonNull
    @ColumnInfo(name = "result_type")
    private String type;

    @ColumnInfo(name = "result_teaser")
    private String wTeaser;

    @ColumnInfo(name = "result_web_url")
    private String wUrl;

    @ColumnInfo(name = "result_youtube_url")
    private String yUrl;

    @ColumnInfo(name = "result_youtube_id")
    private String yId;

    public SearchResultEntity() {
    }

    @Ignore
    public SearchResultEntity(@NonNull String query, @NonNull String name, @NonNull String type, String wTeaser, String wUrl, String yUrl, String yId) {
        this.query = query;
        this.name = name;
        this.type = type;
        this.wTeaser = wTeaser;
        this.wUrl = wUrl;
        this.yUrl = yUrl;
        this.yId = yId;
    }

    @NonNull
    public String getQuery() {
        return query;
    }

    public void setQuery(@NonNull String query) {
        this.query = query;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    public String getWTeaser() {
        return wTeaser;
    }

    public void setWTeaser(String wTeaser) {
        this.wTeaser = wTeaser;
    }

    public String getWUrl() {
        return wUrl;
    }

    public void setWUrl(String wUrl) {
        this.wUrl = wUrl;
    }

    public String getYUrl() {
        return yUrl;
    }

    public void setYUrl(String yUrl) {
        this.yUrl = yUrl;
    }

    public String getYId() {
        return yId;
    }

    public void setYId(String yId) {
        this.yId = yId;
    }
}
