package com.example.kinhangpoon.ebay.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kinhangpoon.ebay.R;

/**
 * Created by KinhangPoon on 24/2/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{
    String[] titles = {"Prescriptions & Health","Shop Products","Photos","Locations"};
    int[] imageUrl = {R.drawable.health,R.drawable.shopping,R.drawable.photo,R.drawable.location};
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.imageViewMain.setImageResource(imageUrl[position]);
        holder.textViewMain.setText(titles[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMain;
        ImageView imageViewMain;
        public MainViewHolder(View itemView) {
            super(itemView);
            textViewMain = itemView.findViewById(R.id.textView_main);
            imageViewMain = itemView.findViewById(R.id.imageView_main);
        }
    }
}
