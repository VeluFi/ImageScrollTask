package com.sam.imagescreening;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageResolution extends AppCompatActivity {
    static int index = 0;
    ImageView imageView;
    String string_Imagepath;
    Handler handler;
    TextView textView_heightwidth, textView_size;
    Button button_image_Original, button_image_25, button_image_50, button_image_75;
    int height, width;
    private float mScaleFactor = 1.0f;
    private ScaleGestureDetector scaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_resolution);
        imageView = findViewById(R.id.imageresol);
        button_image_25 = findViewById(R.id.button_image25);
        textView_heightwidth = findViewById(R.id.width_height);
        textView_size = findViewById(R.id.image_size);
        button_image_50 = findViewById(R.id.button_image50);
        button_image_75 = findViewById(R.id.button_image75);
        button_image_Original = findViewById(R.id.button_imagefull);
        string_Imagepath = "https://www.pixelstalk.net/wp-content/uploads/2016/09/Very-Cool-Wallpapers-HD-620x349.jpg";
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        imageCompress(100);
        button_image_Original.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* imageCompress(100);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setPicture();
                    }
                }, 2000);*/

                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                try {
                    File f = new File(directory.getAbsolutePath(), "profile.jpg");
                    Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                    ImageView img = findViewById(R.id.imageresol);
                    img.setImageBitmap(b);
                    if (f.length() < 1024) {
                        textView_size.setText(f.length() + " Bytes");
                    } else {
                        textView_size.setText(f.length() / 1024 + " KB");
                    }
                    height = imageView.getMeasuredHeight();
                    width = imageView.getMeasuredWidth();
                    textView_heightwidth.setText("Height:" + height + " Width:" + width);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Picasso.with(ImageResolution.this)
                        .load(string_Imagepath)

                        .into(imageView);


            }
        });

        button_image_25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ContextWrapper cw = new ContextWrapper(getApplicationContext());
                    // path to /data/data/yourapp/app_data/imageDir
                    //File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                    // Create imageDir
                    //  File mypath = new File(directory, "resize.jpg");
                    URL url = new URL(string_Imagepath);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);

                    connection.connect();

                    InputStream input = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(input);
                    // Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    // myImage.setImageBitmap(myBitmap);
                    Bitmap converetdImage = getResizedBitmap(bitmap, 225);
                    Bitmap(converetdImage, 100);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        button_image_75.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ContextWrapper cw = new ContextWrapper(getApplicationContext());
                    // path to /data/data/yourapp/app_data/imageDir
                    //File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                    // Create imageDir
                    //  File mypath = new File(directory, "resize.jpg");
                    URL url = new URL(string_Imagepath);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);

                    connection.connect();

                    InputStream input = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(input);
                    // Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    // myImage.setImageBitmap(myBitmap);
                    Bitmap converetdImage = getResizedBitmap(bitmap, 500);
                    Bitmap(converetdImage, 100);
                } catch (IOException e) {
                    e.printStackTrace();
                }
               /* imageCompress(75);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setPicture();
                    }
                }, 2000);*/
            }
        });

        button_image_50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ContextWrapper cw = new ContextWrapper(getApplicationContext());
                    // path to /data/data/yourapp/app_data/imageDir
                    //File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                    // Create imageDir
                    //  File mypath = new File(directory, "resize.jpg");
                    URL url = new URL(string_Imagepath);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);

                    connection.connect();

                    InputStream input = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(input);
                    // Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    // myImage.setImageBitmap(myBitmap);
                    Bitmap converetdImage = getResizedBitmap(bitmap, 375);
                    Bitmap(converetdImage, 100);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Picasso.with(this)
                .load(string_Imagepath)
                .into(imageView);
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private void setPicture() {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, "resize.jpg");
        if (mypath.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(mypath.getAbsolutePath());

            ImageView myImage = findViewById(R.id.imageresol);

            myImage.setImageBitmap(myBitmap);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    private void imageCompress(int quality) {
        try {
            URL url = new URL(string_Imagepath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);

            connection.connect();

            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            //   Bitmap(bitmap, quality);
            saveToInternalStorageOriginal(bitmap, quality);
        } catch (Exception e) {

        }


        // Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        // Bitmap(bitmap, quality);
     /*   handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                loadImageFromStorage(directory.getAbsolutePath());
            }
        }, 2000);*/
    }

    private void Bitmap(Bitmap bitmap, int quality) {
        //   ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // bitmap.compress(Bitmap.CompressFormat.PNG, quality, byteArrayOutputStream);
        saveToInternalStorage(bitmap, quality);

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        loadImageFromStorage(directory.getAbsolutePath());
    }

    private String saveToInternalStorage(Bitmap bitmapImage, int quality) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, "resize.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, quality, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private String saveToInternalStorageOriginal(Bitmap bitmapImage, int quality) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, "profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, quality, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private void loadImageFromStorage(String path) {

        try {
            File f = new File(path, "resize.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img = findViewById(R.id.imageresol);
            img.setImageBitmap(b);
            if (f.length() < 1024) {
                textView_size.setText(f.length() + " Bytes");
            } else {
                textView_size.setText(f.length() / 1024 + " KB");
            }
            height = imageView.getMeasuredHeight();
            width = imageView.getMeasuredWidth();
            textView_heightwidth.setText("Height:" + height + " Width:" + width);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }


}
