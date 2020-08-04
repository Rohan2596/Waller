package com.spatalabz.waller.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.spatalabz.waller.MainActivity;
import com.spatalabz.waller.R;
import com.spatalabz.waller.enums.ApiConstant;
import com.spatalabz.waller.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    private Context context;
    private Category[] categories;
    private onCategoryListener onCategoryListener;


    public CategoryAdapter(Category[] categories,Context context,onCategoryListener onCategoryListener) {
        this.categories=categories;
        this.context=context;
        this.onCategoryListener=onCategoryListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View category_list=layoutInflater.inflate(R.layout.category_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(category_list,onCategoryListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.category_textView.setText(categories[position].category_title);

    }


    @Override
    public int getItemCount() {
        return categories.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView category_textView;
        CardView category_card;
        onCategoryListener onCategoryListener;

        public ViewHolder(@NonNull View itemView,onCategoryListener onCategoryListener) {
            super(itemView);
            this.category_textView=itemView.findViewById(R.id.category_title);
            this.category_card=itemView.findViewById(R.id.category_card);
            this.onCategoryListener=onCategoryListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
          onCategoryListener.onCategoryClick(getAdapterPosition());
        }
    }

public  interface onCategoryListener{
        void onCategoryClick(int position);
}
}
