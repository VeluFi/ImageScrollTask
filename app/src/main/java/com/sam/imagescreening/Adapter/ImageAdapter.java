package com.sam.imagescreening.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.imagescreening.ImageResolution;
import com.sam.imagescreening.R;
import com.squareup.picasso.Picasso;

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

        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ImageResolution.class);
                context.startActivity(i);
            }
        });
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
            imageview = itemView.findViewById(R.id.imageMovie);
        }
    }
}
