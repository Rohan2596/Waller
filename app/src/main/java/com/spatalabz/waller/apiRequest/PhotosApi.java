package com.spatalabz.waller.apiRequest;

import com.spatalabz.waller.model.api.Photo;
import com.spatalabz.waller.model.api.Photos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface PhotosApi {

    @GET("popular")
    Call<Photos> popular(
            @Header("Authorization") String authorization,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
}