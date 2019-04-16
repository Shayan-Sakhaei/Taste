package com.critics.taste.interfaces;

import com.critics.taste.database.entity.Similar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TasteDiveWebservice {

    @GET("/api/similar")
    Call<Similar> getSimilar(@Query("k") String apiKey,
                             @Query("q") String searchQuery,
                             @Query("type") String type,
                             @Query("limit") String limit);
}
