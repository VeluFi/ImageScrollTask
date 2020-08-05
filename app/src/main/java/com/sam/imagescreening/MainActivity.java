package com.sam.imagescreening;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView_imageview;
    String string_Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        recyclerView_imageview = (RecyclerView)findViewById(R.id.image_recyclerview);
        string_Image = "https://www.pixelstalk.net/wp-content/uploads/2016/09/Very-Cool-Wallpapers-HD-620x349.jpg";
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView_imageview.setLayoutManager(gridLayoutManager);
        recyclerView_imageview.setHasFixedSize(true);
        recyclerView_imageview.setItemViewCacheSize(20);
        recyclerView_imageview.setDrawingCacheEnabled(true);
        recyclerView_imageview.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        ImageAdapter customAdapter = new ImageAdapter(this,string_Image );
        recyclerView_imageview.setAdapter(customAdapter);
    }
}
