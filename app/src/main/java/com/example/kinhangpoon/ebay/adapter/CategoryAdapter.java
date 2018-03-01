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
/**
 * *Category Adapter: puts data to the category fragament recyclerView
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    /**
     * Declaration for context,interface,list of category
     */
    List<Category> categories;
    Context context;
    FragmentSwitch fragmentSwitch;

    /**
     * Constructor for CartAdapter
     * @param categories
     * @param context
     * @param fragmentSwitch
     */
    public CategoryAdapter(List<Category> categories,Context context,FragmentSwitch fragmentSwitch) {
        this.categories = categories;
        this.context = context;
        this.fragmentSwitch = fragmentSwitch;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * initialize view
         */
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        /**
         * get the item from category based on positiion
         */
        Category category = categories.get(position);
        final String categoryId = category.getCategoryId();
        /**
         * assign values for items in viewholder
         */
        holder.textViewCategoryName.setText(category.getCategoryName());

        Picasso.with(context).load(category.getCategoryImageUrl()).into(holder.imageViewCategory);
        /**
         * set font for category name
         */
        Typeface typefaceName = Typeface.createFromAsset(context.getAssets(),"THE_JACATRA.otf");
        holder.textViewCategoryName.setTypeface(typefaceName);

        holder.textViewCategoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * go to Subcategory fragment
                 */
                fragmentSwitch.switchToSubCategory(categoryId);
            }
        });

        holder.imageViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * go to Subcategory fragment
                 */
                fragmentSwitch.switchToSubCategory(categoryId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    /**
     * Define ViewHolder for shopping cart
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        /**
         * Declaration
         */
        TextView textViewCategoryName;
        ImageView imageViewCategory;
        public MyViewHolder(View itemView) {
            super(itemView);
            /**
             * initialization
             */
            textViewCategoryName = itemView.findViewById(R.id.textView_category_name);
            imageViewCategory = itemView.findViewById(R.id.imageView_category);

        }
    }

}
