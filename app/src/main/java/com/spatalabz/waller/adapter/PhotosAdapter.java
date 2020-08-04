package com.spatalabz.waller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spatalabz.waller.R;
import com.spatalabz.waller.model.api.Photo;


import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    List<Photo> photos;
    Context context;
    private onPhotoClickListener onPhotoClickListener;

    public PhotosAdapter(List<Photo> photoList, Context context, onPhotoClickListener photoClickListener) {
        this.photos = photoList;
        this.context = context;
        this.onPhotoClickListener = photoClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View photos_list = layoutInflater.inflate(R.layout.photos_list, parent, false);
        PhotosAdapter.ViewHolder viewHolder = new PhotosAdapter.ViewHolder(photos_list, onPhotoClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(photos.get(position).getSrc().getPortrait())
                .into(holder.wallpaper);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView wallpaper;
        onPhotoClickListener onPhotoClickListener;


        public ViewHolder(@NonNull View itemView, onPhotoClickListener onPhotoClickListener) {
            super(itemView);
            this.wallpaper = itemView.findViewById(R.id.wallerpaper);
            this.onPhotoClickListener = onPhotoClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onPhotoClickListener.onPhotoClickListener(getAdapterPosition());

        }
    }


    public interface onPhotoClickListener {
        void onPhotoClickListener(int position);
    }
}
