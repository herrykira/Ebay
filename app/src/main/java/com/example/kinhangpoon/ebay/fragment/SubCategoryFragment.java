package com.example.kinhangpoon.ebay.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.adapter.SubCategoryAdapter;
import com.example.kinhangpoon.ebay.model.SubCategory;
import com.example.kinhangpoon.ebay.network.AppController;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KinhangPoon on 22/2/2018.
 */

/**
 * shows the sub category
 */
public class SubCategoryFragment extends Fragment {
    /**
     * declaration
     */
    SharedPreferences sharedPreferences;
    String subCategoryId, subCategoryName, subCategoryDescription, subCategoryImageUrl;
    List<SubCategory> subCategories;
    SubCategoryAdapter subCategoryAdapter;
    FragmentSwitch fragmentSwitch;
    RecyclerView recyclerView;
    TextView textViewSubCategory;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentSwitch = (FragmentSwitch) getActivity();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sub_category_fragment, container, false);

        /**
         * initialization
         */
        recyclerView = view.findViewById(R.id.recyclerView_sub_category);
        textViewSubCategory = view.findViewById(R.id.textView_sub_category);
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "WILLG___.TTF");
        textViewSubCategory.setTypeface(typeface);
        final String subId = getArguments().getString("CategoryId");
        Log.e("subcategory", subId);
        sharedPreferences = getContext().getSharedPreferences("myinfo", Context.MODE_PRIVATE);
        subCategories = new ArrayList<>();

        /**
         * log in state
         */
        if (!sharedPreferences.getString("UserID", "").equals("") && !sharedPreferences.getString("AppApiKey", "").equals("")) {
            final String api_key = sharedPreferences.getString("AppApiKey", "");
            Log.e("subApi", api_key);
            final String user_id = sharedPreferences.getString("UserID", "");
            Log.e("user_id", user_id);
            String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php?Id=" + subId + "&api_key=" + api_key + "&user_id=" + user_id;
            String tag_json_obj = "json_obj_subcategory";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("subresponse", response.toString());
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("SubCategory");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            subCategoryId = data.getString("Id");
                            subCategoryName = data.getString("SubCatagoryName");
                            subCategoryDescription = data.getString("SubCatagoryDiscription");
                            subCategoryImageUrl = data.getString("CatagoryImage");
                            subCategories.add(new SubCategory(subCategoryId, subCategoryName, subCategoryDescription, subCategoryImageUrl));

                        }
                        subCategoryAdapter = new SubCategoryAdapter(subCategories, getContext(), fragmentSwitch);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(subCategoryAdapter);
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
