package com.spatalabz.waller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class WallpaperActivity extends AppCompatActivity {

    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        Intent getIntent=getIntent();
        imageUrl=getIntent.getStringExtra("Wallpaper");

        ImageView wall=findViewById(R.id.wallpaperImage);
        wall.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        wall.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        wall.setAdjustViewBounds(false);
        wall.setScaleType(ImageView.ScaleType.FIT_XY);
        System.out.println( "WallPaper"+ imageUrl);
        Glide.with(this)
                .load(getIntent.getStringExtra("Wallpaper"))
                .into(wall);
        Button button=findViewById(R.id.wallerSetButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WallpaperActivity.this,"Button Clicked", Toast.LENGTH_SHORT).show();
                setWallpaper();
            }
        });


    }


    private void setWallpaper(){

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.java);
        WallpaperManager manager=WallpaperManager.getInstance(getApplicationContext());
        try {
            manager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}