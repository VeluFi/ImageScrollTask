package com.sam.imagescreening;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.imagescreening.Adapter.ImageAdapter;
import com.sam.imagescreening.network.NetworkConnection;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView_imageview;
    String string_Image;
    private static final String[] PERMISSION_ALL = {Manifest.permission.INTERNET, Manifest.permission.READ_EXTERNAL_STORAGE
            , Manifest.permission.WRITE_EXTERNAL_STORAGE};
    NetworkConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        connection = new NetworkConnection(MainActivity.this);
        permissionCheck();
        recyclerView_imageview = findViewById(R.id.image_recyclerview);

        if (connection.checkInternetConenction()) {
            string_Image = "https://www.pixelstalk.net/wp-content/uploads/2016/09/Very-Cool-Wallpapers-HD-620x349.jpg";
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            recyclerView_imageview.setLayoutManager(gridLayoutManager);
            recyclerView_imageview.setHasFixedSize(true);
            recyclerView_imageview.setItemViewCacheSize(20);
            ImageAdapter customAdapter = new ImageAdapter(this, string_Image);
            recyclerView_imageview.setAdapter(customAdapter);
        } else {
            Toast.makeText(getApplicationContext(), "Cross check your internet connection and try again", Toast.LENGTH_SHORT).show();
        }

    }

    private void permissionCheck() {
        ActivityCompat.requestPermissions(MainActivity.this,
                PERMISSION_ALL,
                1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }



}
