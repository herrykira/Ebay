package com.example.kinhangpoon.ebay.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KinhangPoon on 24/2/2018.
 */
/**
 * *product Adapter: puts data to the product detail recyclerView
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    /**
     * Declaration for list of product and interface
     */
    List<Product> productList;
    Context context;

    /**
     * Constructor for product adapter
     * @param productList
     * @param context
     */
    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * initialize view
         */
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        /**
         * get item from product list
         */
        final Product product = productList.get(position);
        /**
         * assign values for items in viewholder
         */
        holder.textViewDescription.setText(product.getProductDescription());
        holder.textViewPrice.setText("Price: $"+product.getProductPrize());
        holder.textViewQuantity.setText("Quantity: "+product.getProductQuantity());
        holder.textViewProductName.setText("Name: "+product.getProductName());
        Picasso.with(context).load(product.getProductImageUrl()).into(holder.imageViewProduct);

        /**
         * set different fonts for product name, quantity, pirce and description
         */
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"DK Jambo.otf");
        Typeface typeface1 = Typeface.createFromAsset(context.getAssets(),"SF Slapstick Comic.ttf");
        holder.textViewProductName.setTypeface(typeface);
        holder.textViewQuantity.setTypeface(typeface);
        holder.textViewPrice.setTypeface(typeface);
        holder.textViewDescription.setTypeface(typeface1);

        /**
         * set up function for addToCart button: save the product to shopping cart list
         * Use a map(key:item id; value: index of the item) to check whether this item is in shopping cart. If it is in shopping cart, modify the quantity
         * and price of this item in shopping cart list. Or add this item
         */
        holder.buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Product.map.containsKey(product.getProductId())){
                    Product.map.put(product.getProductId(),Product.shoppingCart.size());
                    Log.e("cartSizeBefore",Product.shoppingCart.size()+"");
                    Product.shoppingCart.add(new Product(product.getProductId(),product.getProductName()
                            ,1+"",product.getProductPrize(),product.getProductDescription()
                            ,product.getProductImageUrl()));
                }
                else{
                    double price = Double.valueOf(Product.shoppingCart.get(Product.map.get(product.getProductId())).getProductPrize());
                    int number = Integer.valueOf(Product.shoppingCart.get(Product.map.get(product.getProductId())).getProductQuantity());
                    if(number<Integer.valueOf(product.getProductQuantity())) {
                        Product.shoppingCart.get(Product.map.get(product.getProductId())).setProductQuantity((number + 1) + "");
                        Product.shoppingCart.get(Product.map.get(product.getProductId())).setProductPrize((price + Double.valueOf(product.getProductPrize())) + "");
                    }
                    else{
                        Toast.makeText(context,"You have achieved max number of items",Toast.LENGTH_SHORT).show();
                    }
                }
                Toast.makeText(context,"Successfully add it",Toast.LENGTH_SHORT).show();
                Log.e("cartSizeAfter",Product.shoppingCart.size()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    /**
     * Define ViewHolder for product page
     */
    public class ProductViewHolder extends RecyclerView.ViewHolder {
        /**
         * decalaration
         */
        TextView textViewProductName,textViewQuantity,textViewPrice,textViewDescription;
        ImageView imageViewProduct;
        Button buttonAddToCart;
        public ProductViewHolder(View itemView) {
            super(itemView);
            /**
             * initialization
             */
            textViewProductName = itemView.findViewById(R.id.textView_product_list_name);
            textViewQuantity = itemView.findViewById(R.id.textView_product_list_quantity);
            textViewPrice = itemView.findViewById(R.id.textView_product_list_price);
            textViewDescription = itemView.findViewById(R.id.textView_product_list_description);
            imageViewProduct = itemView.findViewById(R.id.imageView_product);
            buttonAddToCart = itemView.findViewById(R.id.button_add_to_cart);

        }
    }
}
