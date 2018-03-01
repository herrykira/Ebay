package com.example.kinhangpoon.ebay.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.adapter.ProductAdapter;
import com.example.kinhangpoon.ebay.model.Product;
import com.example.kinhangpoon.ebay.network.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KinhangPoon on 23/2/2018.
 */

public class ProductListFragment extends Fragment {
    SharedPreferences sharedPreferences;
    ProductAdapter productAdapter;
    List<Product> products;
    RecyclerView recyclerView;
    TextView textViewProductList;
    String userId, appApiKey;
    FragmentSwitch fragmentSwitch;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentSwitch = (FragmentSwitch) getActivity();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_list_fragment, container, false);
        sharedPreferences = getContext().getSharedPreferences("myinfo", Context.MODE_PRIVATE);
        textViewProductList = view.findViewById(R.id.textView_product_list);
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "WILLG___.TTF");
        textViewProductList.setTypeface(typeface);
        recyclerView = view.findViewById(R.id.recyclerView_product_list);
        products = new ArrayList<>();

        userId = sharedPreferences.getString("UserID", "");
        Log.e("ProductListUser", userId);
        appApiKey = sharedPreferences.getString("AppApiKey", "");
        Log.e("ProductListApi", appApiKey);
        final String subCategoryId = getArguments().getString("SubCategoryId");
        Log.e("ProductsubcategoryId", subCategoryId);

        if (!userId.equals("") && !appApiKey.equals("")) {
            String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_product.php?Id=" + subCategoryId + "&api_key=" + appApiKey + "&user_id=" + userId;
            String tag_json_obj = "json_obj_ProductList";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("productlistResponse", response.toString());
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Product");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            String productId = data.getString("Id");
                            String productName = data.getString("ProductName");
                            String productQuantity = data.getString("Quantity");
                            String productPrize = data.getString("Prize");
                            String productDescription = data.getString("Discription");
                            String productImageUrl = data.getString("Image");

                            products.add(new Product(productId, productName, productQuantity, productPrize, productDescription, productImageUrl));

                        }
                        productAdapter = new ProductAdapter(products, getContext());
                        recyclerView.setAdapter(productAdapter);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {

            };
            AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
        }
        return view;
    }

}
