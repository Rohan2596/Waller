package com.spatalabz.waller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.spatalabz.waller.adapter.CategoryAdapter;
import com.spatalabz.waller.model.Category;

public class MainActivity extends AppCompatActivity {

    RecyclerView category_RecyclerView;
    LinearLayoutManager category_horizontalLayout;


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


    }


}