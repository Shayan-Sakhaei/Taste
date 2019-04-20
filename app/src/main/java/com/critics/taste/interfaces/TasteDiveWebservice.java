package com.critics.taste.interfaces;

import com.critics.taste.database.entity.Api;
import com.critics.taste.database.entity.Similar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TasteDiveWebservice {

    @GET("/api/similar")
    Call<Api> getApiByType(@Query("k") String apiKey,
                           @Query(value = "q", encoded = true) String searchQuery,
                           @Query("type") String type,
                           @Query("limit") String limit);

    @GET("/api/similar")
    Call<Api> getApiMixed(@Query("k") String apiKey,
                          @Query(value = "q", encoded = true) String searchQuery,
                          @Query("limit") String limit);

    @GET("/api/similar?info=1")
    Call<Api> getApiByTypeAndInfo(@Query("k") String apiKey,
                                  @Query(value = "q", encoded = true) String searchQuery,
                                  @Query("type") String type,
                                  @Query("limit") String limit);
}
