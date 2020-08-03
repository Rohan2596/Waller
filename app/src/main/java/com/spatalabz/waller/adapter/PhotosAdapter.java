package com.spatalabz.waller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spatalabz.waller.R;
import com.spatalabz.waller.model.api.Photo;
import com.spatalabz.waller.model.api.Photos;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    List<Photo> photos;
    Context context;

    public PhotosAdapter(List<Photo> photoList, Context context) {
        this.photos=photoList;
        this.context=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View photos_list=layoutInflater.inflate(R.layout.photos_list,parent,false);
        PhotosAdapter.ViewHolder viewHolder=new PhotosAdapter.ViewHolder(photos_list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.photographer.setText(photos.get(position).getPhotographer());
        Glide.with(holder.itemView.getContext())
                .load(photos.get(position).getSrc().getPortrait())
                .into(holder.wallpaper);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView photographer;
        ImageView wallpaper;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            this.photographer=itemView.findViewById(R.id.photographer);
            this.wallpaper=itemView.findViewById(R.id.wallerpaper);
        }
    }
}
