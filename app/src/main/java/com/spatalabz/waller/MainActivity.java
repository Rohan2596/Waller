package com.spatalabz.waller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.spatalabz.waller.adapter.CategoryAdapter;
import com.spatalabz.waller.apiRequest.ApiClient;
import com.spatalabz.waller.apiRequest.PhotosApi;
import com.spatalabz.waller.model.Category;
import com.spatalabz.waller.model.api.Photo;
import com.spatalabz.waller.model.api.Photos;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView category_RecyclerView;
    LinearLayoutManager category_horizontalLayout;
    Photo photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Category[] categories = new Category[]{
                new Category("Nature"),
                new Category("Cars"),
                new Category("Bikes"),
                new Category("Sports"),
                new Category("Food"),
                new Category("Animals"),
                new Category("Flowers"),
                new Category("Friends"),
                new Category("Avengers"),
                new Category("Technology"),
                new Category("Science")

        };
        category_RecyclerView = findViewById(R.id.category_recyclerView);

        category_horizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        category_RecyclerView.setLayoutManager(category_horizontalLayout);
        category_RecyclerView.setAdapter(new CategoryAdapter(categories,getApplicationContext()));

           loadPhotos();

    }

    private void loadPhotos(){

        PhotosApi photosApi= ApiClient.getClient().create(PhotosApi.class);
        Call<Photos> call=photosApi.popular(
                "563492ad6f91700001000001db74f1b0e3e744bab29c433580253e36",
                1,
                30
        );
        call.enqueue(new Callback<Photos>() {
            @Override
            public void onResponse(Call<Photos> call, Response<Photos> response) {
                if (response.isSuccessful()){
                    Photos photos=response.body();
                    Toast.makeText(MainActivity.this,"photos"+ photos.getPer_page(),Toast.LENGTH_SHORT).show();
                    Log.i("Photograopher ",response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Photos> call, Throwable t) {
                Log.i("Photograopher ","EROROROR");

            }
        });



    }


}