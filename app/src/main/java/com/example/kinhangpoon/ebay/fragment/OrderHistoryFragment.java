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
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.adapter.OrderHistoryAdapter;
import com.example.kinhangpoon.ebay.model.OrderHistory;
import com.example.kinhangpoon.ebay.network.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KinhangPoon on 25/2/2018.
 */

public class OrderHistoryFragment extends Fragment {
    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    TextView textViewOrderHistory;
    List<OrderHistory> orderHistories;
    OrderHistoryAdapter orderHistoryAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.order_history_fragment,container,false);
        recyclerView = view.findViewById(R.id.recyclerView_order_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));

        recyclerView.setHasFixedSize(true);
        sharedPreferences = getContext().getSharedPreferences("myinfo", Context.MODE_PRIVATE);
        textViewOrderHistory = view.findViewById(R.id.textView_order_history);
        String mobile = sharedPreferences.getString("Mobile","");
        Log.i("orderHistory",mobile);
        orderHistories = new ArrayList<>();

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"WILLG___.TTF");
        textViewOrderHistory.setTypeface(typeface);

        String  userId = sharedPreferences.getString("UserID","");
        Log.i("orderHistoryId",userId);
        String appApiKey = sharedPreferences.getString("AppApiKey","");
        Log.i("orderHistoryapi",appApiKey);

        if(!userId.equals("") && !appApiKey.equals("")){
            //http://rjtmobile.com/ansari/shopingcart/androidapp/order_history.php?&mobile=9294228483&api_key=9c775e7dd06f5cfc5fb3b94dc1ea16ae&user_id=1196

            String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/order_history.php?&mobile="+mobile+"&api_key="+appApiKey+"&user_id="+userId;
            String tag_json_obj = "json_obj_order_history";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("orderHistory",response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Order History");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject data = jsonArray.getJSONObject(i);
                            String OrderID = data.getString("OrderID");
                            String ItemName = data.getString("ItemName");
                            String ItemQuantity = data.getString("ItemQuantity");
                            String FinalPrice = data.getString("FinalPrice");
                            String OrderStatus = data.getString("OrderStatus");
                            orderHistories.add(new OrderHistory(OrderID,ItemName,ItemQuantity,FinalPrice,OrderStatus));

                        }
                        orderHistoryAdapter = new OrderHistoryAdapter(orderHistories,getContext());
                        recyclerView.setAdapter(orderHistoryAdapter);
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
