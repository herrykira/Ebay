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
import com.example.kinhangpoon.ebay.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KinhangPoon on 24/2/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    List<Product> productList;
    Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.textViewDescription.setText(product.getProductDescription());
        holder.textViewPrice.setText("Price: $"+product.getProductPrize());
        holder.textViewQuantity.setText("Quantity: "+product.getProductQuantity());
        holder.textViewProductName.setText("Name: "+product.getProductName());
        Picasso.with(context).load(product.getProductImageUrl()).into(holder.imageViewProduct);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"DK Jambo.otf");
        Typeface typeface1 = Typeface.createFromAsset(context.getAssets(),"SF Slapstick Comic.ttf");
        holder.textViewProductName.setTypeface(typeface);
        holder.textViewQuantity.setTypeface(typeface);
        holder.textViewPrice.setTypeface(typeface);
        holder.textViewDescription.setTypeface(typeface1);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductName,textViewQuantity,textViewPrice,textViewDescription;
        ImageView imageViewProduct;
        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.textView_product_list_name);
            textViewQuantity = itemView.findViewById(R.id.textView_product_list_quantity);
            textViewPrice = itemView.findViewById(R.id.textView_product_list_price);
            textViewDescription = itemView.findViewById(R.id.textView_product_list_description);
            imageViewProduct = itemView.findViewById(R.id.imageView_product);

        }
    }
}
