package com.example.kinhangpoon.ebay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.fragment.FragmentSwitch;
import com.example.kinhangpoon.ebay.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KinhangPoon on 22/2/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    List<Category> categories;
    Context context;
    FragmentSwitch fragmentSwitch;

    public CategoryAdapter(List<Category> categories,Context context,FragmentSwitch fragmentSwitch) {
        this.categories = categories;
        this.context = context;
        this.fragmentSwitch = fragmentSwitch;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Category category = categories.get(position);
        holder.textViewTitle.setText(category.getCategoryName());
        Picasso.with(context).load(category.getCategoryImageUrl()).into(holder.imageViewTitle);

        holder.textViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitch.switchToProductDetail(position);
            }
        });

        holder.imageViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitch.switchToProductDetail(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        ImageView imageViewTitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textView_category_title);
            imageViewTitle = itemView.findViewById(R.id.imageView_category_title);
        }
    }

}
