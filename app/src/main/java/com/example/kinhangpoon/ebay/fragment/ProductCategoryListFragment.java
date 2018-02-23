package com.example.kinhangpoon.ebay.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.adapter.CategoryAdapter;
import com.example.kinhangpoon.ebay.model.Category;
import com.example.kinhangpoon.ebay.network.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KinhangPoon on 22/2/2018.
 */

public class ProductCategoryListFragment extends Fragment {
    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    List<Category> categories;
    CategoryAdapter categoryAdapter;
    FragmentSwitch fragmentSwitch;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentSwitch = (FragmentSwitch) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_category_list_fragment,container,false);
        sharedPreferences = getContext().getSharedPreferences("myinfo", Context.MODE_PRIVATE);
        recyclerView = view.findViewById(R.id.recyclerView_product_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        categories = new ArrayList<>();

        if(!sharedPreferences.getString("UserID","").equals("") && !sharedPreferences.getString("AppApiKey","").equals("")){
            String userId = sharedPreferences.getString("UserID","");
            Log.i("shareuserId",userId);
            String appApiKey = sharedPreferences.getString("AppApiKey","");
            Log.i("shareapi",appApiKey);

            String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key="+appApiKey+"&user_id="+userId;
            String tag_json_obj = "json_obj_productlist";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("productlist",response.toString());
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Category");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject data = jsonArray.getJSONObject(i);
                            String categoryName = data.getString("CatagoryName");
                            String categoryDescription = data.getString("CatagoryDiscription");
                            String categoryImageUrl = data.getString("CatagoryImage");

                            categories.add(new Category(categoryName,categoryDescription,categoryImageUrl));
                        }
                        Category.categoryList = categories;
                        categoryAdapter = new CategoryAdapter(categories,getContext(),fragmentSwitch);
                        recyclerView.setAdapter(categoryAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            AppController.getInstance().addToRequestQueue(stringRequest,tag_json_obj);
        }
        return view;
    }
}
