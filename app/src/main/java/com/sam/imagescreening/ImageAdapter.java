package com.sam.imagescreening;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {
    Context context;
    String image;


    public ImageAdapter(Activity mainActivity, String string_image) {
        this.context = mainActivity;
        this.image = string_image;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_image_items, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       /* InputStream is = null;
        try {
            is = (InputStream) new URL(image).getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(is, "src name");
        holder.imageview.setImageDrawable(d);*/


        Picasso.with(context)
                .load(image)
                .fit()
                .centerCrop()
                .into(holder.imageview);
    }


    @Override
    public int getItemCount() {
        return 1000;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview;// init the item view's
        public MyViewHolder(View itemView) {
            super(itemView);

// get the reference of item view's
            imageview = (ImageView) itemView.findViewById(R.id.imageMovie);
        }
    }
}
