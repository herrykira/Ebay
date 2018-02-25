package com.example.kinhangpoon.ebay.adapter;

import android.content.Context;
import android.graphics.Typeface;
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
        final String categoryId = category.getCategoryId();
        holder.textViewCategoryName.setText(category.getCategoryName());
//        holder.textViewCategoryDescription.setText(category.getCategoryDescription());
        Picasso.with(context).load(category.getCategoryImageUrl()).into(holder.imageViewCategory);
        Typeface typefaceName = Typeface.createFromAsset(context.getAssets(),"THE_JACATRA.otf");
        holder.textViewCategoryName.setTypeface(typefaceName);

//        holder.textViewCategoryDescription.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragmentSwitch.switchToSubCategory(categoryId);
//            }
//        });
        holder.textViewCategoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitch.switchToSubCategory(categoryId);
            }
        });

        holder.imageViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitch.switchToSubCategory(categoryId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCategoryName;
//        TextView textViewCategoryDescription;
        ImageView imageViewCategory;
        public MyViewHolder(View itemView) {
            super(itemView);
            textViewCategoryName = itemView.findViewById(R.id.textView_category_name);
            imageViewCategory = itemView.findViewById(R.id.imageView_category);
//            textViewCategoryDescription = itemView.findViewById(R.id.textView_category_description);
        }
    }

}
