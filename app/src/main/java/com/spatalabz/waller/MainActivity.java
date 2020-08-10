package com.spatalabz.waller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
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


/*
* Author : Rohan Kadam.
* Purpose: Activity used for displaying Category ,Edittext, photos
*          It also consist of various features for handling events.
* */

public class MainActivity extends AppCompatActivity implements CategoryAdapter.onCategoryListener, PhotosAdapter.onPhotoClickListener {

    RecyclerView category_RecyclerView;
    LinearLayoutManager category_horizontalLayout;
    List<Photo> photoList;
    RecyclerView photos_RecyclerView;
    StaggeredGridLayoutManager photos_GridLayout;
    PhotosAdapter photosAdapter;
    Category[] categories;
    EditText search_text;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_text=findViewById(R.id.search_text);

           search_text.setOnKeyListener(new View.OnKeyListener() {
               @Override
               public boolean onKey(View view, int i, KeyEvent keyEvent) {

                   if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                           (i == KeyEvent.KEYCODE_ENTER)) {
                       // Perform action on key press
                       Toast.makeText(MainActivity.this, search_text.getText(), Toast.LENGTH_SHORT).show();
                       onSearchCall(search_text.getText().toString());
                       return true;
                   }
                   return false;
               }
           });
        categories = new Category[]{
                new Category("Nature"),
                new Category("Cars"),
                new Category("Bikes"),
                new Category("Sports"),
                new Category("Brands"),
                new Category("Animals"),
                new Category("Flowers"),
                new Category("Friends"),
                new Category("City"),
                new Category("Technology"),
                new Category("Science")

        };
        category_RecyclerView = findViewById(R.id.category_recyclerView);

        category_horizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        category_RecyclerView.setLayoutManager(category_horizontalLayout);
        category_RecyclerView.setAdapter(new CategoryAdapter(categories, getApplicationContext(), this));

        loadPhotos();


    }

    private void loadPhotos() {
        PhotosApi photosApi = ApiClient.getClient().create(PhotosApi.class);
        Call<Photos> call = null;

        call = photosApi.popular(
                "563492ad6f91700001000001db74f1b0e3e744bab29c433580253e36",
                1,
                800
        );

        callResponse(call);

    }


    public void onSearchCall(String query){
        PhotosApi photosApi = ApiClient.getClient().create(PhotosApi.class);
        Call<Photos> call = photosApi.search(
                "563492ad6f91700001000001db74f1b0e3e744bab29c433580253e36",
                query,
                80
        );
        callResponse(call);
    }

    @Override
    public void onCategoryClick(int position) {
        String query = categories[position].category_title;
        onSearchCall(query);
    }

    @Override
    public void onPhotoClickListener(int position) {
        Intent toWaller = new Intent(MainActivity.this, WallpaperActivity.class);
        toWaller.putExtra("Wallpaper", photoList.get(position).getSrc().getPortrait());
        startActivity(toWaller);
    }

    private void callResponse(Call<Photos> call) {
        call.enqueue(new Callback<Photos>() {
            @Override
            public void onResponse(Call<Photos> call, Response<Photos> response) {
                if (response.isSuccessful()) {
                    Photos photos = response.body();
                    photoList = response.body().getPhotos();
                    Toast.makeText(MainActivity.this, "Wallpaper:- " + photos.getPer_page(), Toast.LENGTH_SHORT).show();
                    Log.i("Photograopher ", response.body().toString());

                    photos_RecyclerView = findViewById(R.id.photos_recyclerView);
                    photos_GridLayout = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    photos_RecyclerView.setLayoutManager(photos_GridLayout);
                    photosAdapter = new PhotosAdapter(photoList, getApplicationContext(), MainActivity.this::onPhotoClickListener);
                    photos_RecyclerView.setAdapter(photosAdapter);
                    photosAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<Photos> call, Throwable t) {
                Log.i("Photograopher ", "EROROROR");

            }
        });
    }
}