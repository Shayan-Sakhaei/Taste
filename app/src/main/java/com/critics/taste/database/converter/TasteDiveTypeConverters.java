package com.critics.taste.database.converter;

import android.arch.persistence.room.TypeConverter;

import com.critics.taste.database.entity.SearchResultEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class TasteDiveTypeConverters {

    private static final Gson gson = new Gson();

    @TypeConverter
    public static List<SearchResultEntity> stringToSearchResultList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<SearchResultEntity>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String searchResultListToString(List<SearchResultEntity> searchResultEntityList) {
        return gson.toJson(searchResultEntityList);
    }
}
