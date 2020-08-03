package com.spatalabz.waller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.spatalabz.waller.adapter.CategoryAdapter;
import com.spatalabz.waller.adapter.PhotosAdapter;
import com.spatalabz.waller.apiRequest.ApiClient;
import com.spatalabz.waller.apiRequest.PhotosApi;
import com.spatalabz.waller.model.Category;
import com.spatalabz.waller.model.api.Photo;
import com.spatalabz.waller.model.api.Photos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView category_RecyclerView;
    LinearLayoutManager category_horizontalLayout;
    Photo photo ;
    List<Photo> photoList;
    RecyclerView photos_RecyclerView;
    StaggeredGridLayoutManager photos_GridLayout;


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
                    photoList=response.body().getPhotos();
                    Toast.makeText(MainActivity.this,"photos"+ photos.getPer_page(),Toast.LENGTH_SHORT).show();
                    Log.i("Photograopher ",response.body().toString());

                    photos_RecyclerView=findViewById(R.id.photos_recyclerView);
                    photos_GridLayout=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                    photos_RecyclerView.setLayoutManager(photos_GridLayout);
                    photos_RecyclerView.setAdapter(new PhotosAdapter(photoList,getApplicationContext()));
                }
            }

            @Override
            public void onFailure(Call<Photos> call, Throwable t) {
                Log.i("Photograopher ","EROROROR");

            }
        });



    }


}