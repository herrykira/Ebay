package com.example.kinhangpoon.ebay.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kinhangpoon.ebay.R;

/**
 * Created by KinhangPoon on 21/2/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_login_register,parent,false);
                return new ViewHolderUser(view);

            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show,parent,false);
                return new ViewHolderItem(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 * 2;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case 0:
                ViewHolderUser viewHolderUser = (ViewHolderUser) holder;
                viewHolderUser.buttonMainRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                viewHolderUser.buttonMainLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case 2:
                ViewHolderItem viewHolderItem = (ViewHolderItem) holder;
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderUser extends RecyclerView.ViewHolder{
        TextView textViewLoginRegister;
        Button buttonMainLogin, buttonMainRegister;
        public ViewHolderUser(View itemView) {
            super(itemView);
            textViewLoginRegister = itemView.findViewById(R.id.textView_login_register);
            buttonMainLogin = itemView.findViewById(R.id.button_main_login);
            buttonMainRegister = itemView.findViewById(R.id.button_main_register);
        }
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {
        ImageView imageViewitem;
        public ViewHolderItem(View itemView) {
            super(itemView);
            imageViewitem = itemView.findViewById(R.id.imageView_item);

        }
    }
}
