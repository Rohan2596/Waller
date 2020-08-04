package com.spatalabz.waller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class WallpaperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

        Intent getIntent=getIntent();
        ImageView wall=findViewById(R.id.wallpaperImage);
        wall.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        wall.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        wall.setAdjustViewBounds(false);
        wall.setScaleType(ImageView.ScaleType.FIT_XY);
        System.out.println( "WallPaper"+ getIntent.getStringExtra("Wallpaper"));
        Glide.with(this)
                .load(getIntent.getStringExtra("Wallpaper"))
                .into(wall);


    }
}