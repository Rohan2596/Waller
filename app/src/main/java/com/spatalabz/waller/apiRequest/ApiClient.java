package com.spatalabz.waller.apiRequest;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
* Author : Rohan Kadam.
* Purpose: Creating an retrofit client using baseUrl.
* */
public class ApiClient {


    public static String BASE_URL = "https://api.pexels.com/v1/";
    private static Retrofit retrofit;



    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
