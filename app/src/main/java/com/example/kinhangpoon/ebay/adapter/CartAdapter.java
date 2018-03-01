package com.example.kinhangpoon.ebay.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.fragment.FragmentSwitch;
import com.example.kinhangpoon.ebay.model.Product;
import com.squareup.picasso.Picasso;

/**
 * Created by KinhangPoon on 26/2/2018.
 */

/**
 * *Shopping Cart Adapter: puts data to the shopping cart recyclerView
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    /**
     * Declaration for context and interface
     */
    Context context;
    FragmentSwitch fragmentSwitch;

    /**
     * Constructor for CartAdapter
     * @param context
     * @param fragmentSwitch
     */
    public CartAdapter(Context context,FragmentSwitch fragmentSwitch) {
        this.context = context;
        this.fragmentSwitch = fragmentSwitch;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * initialize view
         */
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, final int position) {
        /**
         * get the item from shopping cart based on position
         */
        Product product = Product.shoppingCart.get(position);
        /**
         * assign values for items in viewholder
         */
        holder.textViewProductCartName.setText("Name: "+product.getProductName());
        holder.textViewProductCartQuantity.setText("Quantity: "+product.getProductQuantity());
        holder.textViewProductCartPrice.setText("Price: $"+product.getProductPrize());
        Picasso.with(context).load(product.getProductImageUrl()).into(holder.imageViewProductCart);
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * set up alertDialog
                 */
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Alert");
                builder.setMessage("Are you sure to delete it ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Product.shoppingCart.remove(position);
                        fragmentSwitch.switchToCart();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Product.shoppingCart.size();
    }

    /**
     * Define ViewHolder for shopping cart
     */
    public class CartViewHolder extends RecyclerView.ViewHolder {
        /**
         * Declaration
         */
        TextView textViewProductCartName,textViewProductCartQuantity,textViewProductCartPrice;
        ImageView imageViewProductCart;
        Button buttonDelete;
        public CartViewHolder(View itemView) {
            super(itemView);
            /**
             * initialization
             */
            textViewProductCartName = itemView.findViewById(R.id.textView_product_name_cart);
            textViewProductCartPrice = itemView.findViewById(R.id.textView_product_price_cart);
            textViewProductCartQuantity = itemView.findViewById(R.id.textView_product_quantity_cart);
            imageViewProductCart = itemView.findViewById(R.id.imageView_product_cart);
            buttonDelete = itemView.findViewById(R.id.button_delete_cart_item);
        }
    }
}
