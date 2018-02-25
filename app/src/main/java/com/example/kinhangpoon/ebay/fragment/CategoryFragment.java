package com.example.kinhangpoon.ebay.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
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

public class CategoryFragment extends Fragment {
    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    List<Category> categories;
    CategoryAdapter categoryAdapter;
    FragmentSwitch fragmentSwitch;
    TextView textViewCategory;
    String userId,appApiKey;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentSwitch = (FragmentSwitch) getActivity();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_category_list_fragment,container,false);
        sharedPreferences = getContext().getSharedPreferences("myinfo", Context.MODE_PRIVATE);
        textViewCategory = view.findViewById(R.id.textView_category);
        recyclerView = view.findViewById(R.id.recyclerView_category);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), GridLayoutManager.VERTICAL));
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"WILLG___.TTF");
        textViewCategory.setTypeface(typeface);
        categories = new ArrayList<>();

        if(!sharedPreferences.getString("UserID","").equals("") && !sharedPreferences.getString("AppApiKey","").equals("")){
             userId = sharedPreferences.getString("UserID","");
            Log.i("shareuserId",userId);
             appApiKey = sharedPreferences.getString("AppApiKey","");
            Log.i("shareapi",appApiKey);

            String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key="+appApiKey+"&user_id="+userId;
            String tag_json_obj = "json_obj_category";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("productlist",response.toString());
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Category");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject data = jsonArray.getJSONObject(i);
                            String categoryId = data.getString("Id");
                            String categoryName = data.getString("CatagoryName");
                            String categoryDescription = data.getString("CatagoryDiscription");
                            String categoryImageUrl = data.getString("CatagoryImage");

                            categories.add(new Category(categoryId,categoryName,categoryDescription,categoryImageUrl));
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

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.logoutmenu,menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        sharedPreferences = getContext().getSharedPreferences("myinfo", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("UserID","");
//            editor.putString("AppApiKey","");
//            editor.commit();
//            userId = sharedPreferences.getString("UserID","");
//            appApiKey = sharedPreferences.getString("AppApiKey","");
//            Log.i("menu",userId);
//            Log.i("menu",appApiKey);
//        fragmentSwitch.switchToMain();
//        return super.onOptionsItemSelected(item);
//    }
}
