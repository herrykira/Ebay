package com.example.kinhangpoon.ebay.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.fragment.FragmentSwitch;

/**
 * Created by KinhangPoon on 24/2/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{
    String[] titles = {"Prescriptions & Health","Shop Products","Photos","Locations"};
    int[] imageUrl = {R.drawable.health,R.drawable.shopping,R.drawable.photo,R.drawable.location};
    Context context;
    FragmentSwitch fragmentSwitch;

    public MainAdapter(Context context,FragmentSwitch fragmentSwitch) {
        this.context = context;
        this.fragmentSwitch = fragmentSwitch;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {
        holder.imageViewMain.setImageResource(imageUrl[position]);
        holder.textViewMain.setText(titles[position]);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"Sugar & Vinegar.ttf");
        holder.textViewMain.setTypeface(typeface);

        holder.imageViewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFragment(position);
            }
        });

        holder.textViewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFragment(position);
            }
        });
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

    public void chooseFragment(int position){
        switch (position){
            case 1:
                fragmentSwitch.switchToCategory();
                break;

        }
    }
}
