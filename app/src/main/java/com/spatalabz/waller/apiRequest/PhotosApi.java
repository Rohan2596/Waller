package com.spatalabz.waller.apiRequest;

import com.spatalabz.waller.model.api.Photos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


/*
* Author : Rohan Kadam.
* Purpose: Creating Api Call Events for application.
* */
public interface PhotosApi {

    @GET("popular")
    Call<Photos> popular(
            @Header("Authorization") String authorization,
            @Query("page") int page,
            @Query("per_page") int perPage
    );

    @GET("search")
    Call<Photos> search(
            @Header("Authorization") String authorization,
            @Query("query") String query,
            @Query("per_page") int perPage

    );
}
