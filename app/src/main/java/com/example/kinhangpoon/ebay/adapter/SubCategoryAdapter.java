package com.example.kinhangpoon.ebay.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.fragment.FragmentSwitch;
import com.example.kinhangpoon.ebay.model.SubCategory;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KinhangPoon on 23/2/2018.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {
    List<SubCategory> subCategoryList;
    Context context;
    FragmentSwitch fragmentSwitch;
    Animation animation;

    public SubCategoryAdapter(List<SubCategory> subCategoryList, Context context,FragmentSwitch fragmentSwitch) {
        this.subCategoryList = subCategoryList;
        this.context = context;
        this.fragmentSwitch = fragmentSwitch;
    }

    @Override
    public SubCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_item,parent,false);
        return new SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubCategoryViewHolder holder, int position) {
        SubCategory subCategory = subCategoryList.get(position);
        final String subCategoryId = subCategory.getSubCategoryId();
        holder.textViewSubCategoryName.setText(subCategory.getSubCategoryName());
        holder.textViewSubCategoryDescription.setText(subCategory.getSubCategoryDescription());
        Picasso.with(context).load(subCategory.getSubCategoryImageUrl()).into(holder.imageViewSubCategory);

    //animation
      animation = AnimationUtils.loadAnimation(context,R.anim.fade_in);
      holder.imageViewSubCategory.startAnimation(animation);
      holder.textViewSubCategoryDescription.startAnimation(animation);
      holder.textViewSubCategoryName.startAnimation(animation);

      //fonts
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"OldNewspaperTypes.ttf");
        holder.textViewSubCategoryName.setTypeface(typeface);
        holder.textViewSubCategoryDescription.setTypeface(typeface);

        holder.textViewSubCategoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitch.switchToDetail(subCategoryId);
            }
        });
        holder.textViewSubCategoryDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitch.switchToDetail(subCategoryId);
            }
        });
        holder.imageViewSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitch.switchToDetail(subCategoryId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSubCategoryName,textViewSubCategoryDescription;
        ImageView imageViewSubCategory;
        public SubCategoryViewHolder(View itemView) {
            super(itemView);
            textViewSubCategoryName = itemView.findViewById(R.id.textView_sub_category_name);
            textViewSubCategoryDescription = itemView.findViewById(R.id.textView_sub_category_description);
            imageViewSubCategory = itemView.findViewById(R.id.imageView_sub_category_name);
        }
    }
}
