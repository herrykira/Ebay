package com.example.kinhangpoon.ebay.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.adapter.CartAdapter;
import com.example.kinhangpoon.ebay.model.Product;
import com.example.kinhangpoon.ebay.network.AppController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KinhangPoon on 26/2/2018.
 */

public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    FragmentSwitch fragmentSwitch;
    Button buttonOrder,buttonCancel;
    SharedPreferences sharedPreferences;
    String userId,appApiKey,mobile;
    TextView textViewShoppingCart;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentSwitch = (FragmentSwitch) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment,container,false);
        recyclerView = view.findViewById(R.id.recyclerView_shopping_cart);
        textViewShoppingCart = view.findViewById(R.id.textView_shopping_cart);

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "WILLG___.TTF");
        textViewShoppingCart.setTypeface(typeface);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        cartAdapter = new CartAdapter(getContext(),fragmentSwitch);
        recyclerView.setAdapter(cartAdapter);

        sharedPreferences = getContext().getSharedPreferences("myinfo",Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("UserID","");
        Log.e("CartUserID",userId+"");
        appApiKey = sharedPreferences.getString("AppApiKey","");
        Log.e("CartAppApiKey",appApiKey+"");
        mobile = sharedPreferences.getString("Mobile","");
        Log.e("CartMobile",mobile+"");

        buttonCancel = view.findViewById(R.id.button_cancel_order);
        buttonOrder = view.findViewById(R.id.button_place_order);

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userId.equals("") && !appApiKey.equals("")) {
                    List<Product> products = Product.shoppingCart;

                    for (int i = 0; i < products.size(); i++) {
                        Product product = products.get(i);
                        String productId = product.getProductId();
                        String productName = product.getProductName();
                        String productQuantity = product.getProductQuantity();
                        String productPrice = product.getProductPrize();
                        String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/orders.php?&item_id="+productId+"&item_names="+productName+"&item_quantity="+productQuantity+
                                "&final_price="+productPrice+"&mobile="+mobile+"&api_key="+appApiKey+"&user_id="+userId;
                        String tag_json_obj = "json_obj_order"+i;
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("orderResponse",response.toString());
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        AppController.getInstance().addToRequestQueue(stringRequest,tag_json_obj);
                    }
                    Product.shoppingCart = new ArrayList<>();
                    fragmentSwitch.switchToCategory();
                }
                else{
                    fragmentSwitch.switchToLogin();
                }
            }
        });
        return view;
    }
}
