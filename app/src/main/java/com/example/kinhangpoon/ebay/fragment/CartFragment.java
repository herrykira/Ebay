package com.example.kinhangpoon.ebay.fragment;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.adapter.CartAdapter;
import com.example.kinhangpoon.ebay.model.Product;
import com.example.kinhangpoon.ebay.network.AppController;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

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
    String clientToken="eyJ2ZXJzaW9uIjoyLCJhdXRob3JpemF0aW9uRmluZ2VycHJpbnQiOiJjMTliYjFlNTExNzdhNzFjODJiMWU0YjBjYTQ0ZjBlMGY0YjNhZWZjN2MzYjYyYjkzZDdlYjU3NDBjODA3ZDMyfGNyZWF0ZWRfYXQ9MjAxOC0wMi0yN1QxNDo0Njo0OC43NzkzNjA5NzIrMDAwMFx1MDAyNm1lcmNoYW50X2lkPTM0OHBrOWNnZjNiZ3l3MmJcdTAwMjZwdWJsaWNfa2V5PTJuMjQ3ZHY4OWJxOXZtcHIiLCJjb25maWdVcmwiOiJodHRwczovL2FwaS5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tOjQ0My9tZXJjaGFudHMvMzQ4cGs5Y2dmM2JneXcyYi9jbGllbnRfYXBpL3YxL2NvbmZpZ3VyYXRpb24iLCJjaGFsbGVuZ2VzIjpbXSwiZW52aXJvbm1lbnQiOiJzYW5kYm94IiwiY2xpZW50QXBpVXJsIjoiaHR0cHM6Ly9hcGkuc2FuZGJveC5icmFpbnRyZWVnYXRld2F5LmNvbTo0NDMvbWVyY2hhbnRzLzM0OHBrOWNnZjNiZ3l3MmIvY2xpZW50X2FwaSIsImFzc2V0c1VybCI6Imh0dHBzOi8vYXNzZXRzLmJyYWludHJlZWdhdGV3YXkuY29tIiwiYXV0aFVybCI6Imh0dHBzOi8vYXV0aC52ZW5tby5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tIiwiYW5hbHl0aWNzIjp7InVybCI6Imh0dHBzOi8vY2xpZW50LWFuYWx5dGljcy5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tLzM0OHBrOWNnZjNiZ3l3MmIifSwidGhyZWVEU2VjdXJlRW5hYmxlZCI6dHJ1ZSwicGF5cGFsRW5hYmxlZCI6dHJ1ZSwicGF5cGFsIjp7ImRpc3BsYXlOYW1lIjoiQWNtZSBXaWRnZXRzLCBMdGQuIChTYW5kYm94KSIsImNsaWVudElkIjpudWxsLCJwcml2YWN5VXJsIjoiaHR0cDovL2V4YW1wbGUuY29tL3BwIiwidXNlckFncmVlbWVudFVybCI6Imh0dHA6Ly9leGFtcGxlLmNvbS90b3MiLCJiYXNlVXJsIjoiaHR0cHM6Ly9hc3NldHMuYnJhaW50cmVlZ2F0ZXdheS5jb20iLCJhc3NldHNVcmwiOiJodHRwczovL2NoZWNrb3V0LnBheXBhbC5jb20iLCJkaXJlY3RCYXNlVXJsIjpudWxsLCJhbGxvd0h0dHAiOnRydWUsImVudmlyb25tZW50Tm9OZXR3b3JrIjp0cnVlLCJlbnZpcm9ubWVudCI6Im9mZmxpbmUiLCJ1bnZldHRlZE1lcmNoYW50IjpmYWxzZSwiYnJhaW50cmVlQ2xpZW50SWQiOiJtYXN0ZXJjbGllbnQzIiwiYmlsbGluZ0FncmVlbWVudHNFbmFibGVkIjp0cnVlLCJtZXJjaGFudEFjY291bnRJZCI6ImFjbWV3aWRnZXRzbHRkc2FuZGJveCIsImN1cnJlbmN5SXNvQ29kZSI6IlVTRCJ9LCJtZXJjaGFudElkIjoiMzQ4cGs5Y2dmM2JneXcyYiIsInZlbm1vIjoib2ZmIn0=";
    static final int BRAIN_TREE_REQUEST_CODE = 4949;

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
                //paypal
                onBrainTreeSubmit(v);
//                if (!userId.equals("") && !appApiKey.equals("")) {
//                    List<Product> products = Product.shoppingCart;
//
//                    for (int i = 0; i < products.size(); i++) {
//                        Product product = products.get(i);
//                        String productId = product.getProductId();
//                        String productName = product.getProductName();
//                        String productQuantity = product.getProductQuantity();
//                        String productPrice = product.getProductPrize();
//                        String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/orders.php?&item_id="+productId+"&item_names="+productName+"&item_quantity="+productQuantity+
//                                "&final_price="+productPrice+"&mobile="+mobile+"&api_key="+appApiKey+"&user_id="+userId;
//                        String tag_json_obj = "json_obj_order"+i;
//                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                Log.e("orderResponse",response.toString());
//                            }
//                        }, new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//
//                            }
//                        });
//                        AppController.getInstance().addToRequestQueue(stringRequest,tag_json_obj);
//                    }
//                    Product.shoppingCart = new ArrayList<>();
//                    fragmentSwitch.switchToCategory();
//                }
//                else{
//                    fragmentSwitch.switchToLogin();
//                }
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitch.switchToCategory();
            }
        });
        return view;
    }

    public void onBrainTreeSubmit(View view){
        DropInRequest dropInRequest = new DropInRequest().clientToken(clientToken);
        startActivityForResult(dropInRequest.getIntent(getContext()),BRAIN_TREE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == BRAIN_TREE_REQUEST_CODE){
            if(RESULT_OK == resultCode){
                DropInResult dropInResult = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                String payment_notice = dropInResult.getPaymentMethodNonce().getNonce();
                Log.e("Successful","sucessful");
            }
            else if (requestCode == RESULT_CANCELED){
                Log.e("Canceled","User canceled payment");
            }
            else {
                Exception exception = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
                Log.e("Error","Error");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void getClientTokenFromAppServer(){
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("Token Failed","Failed Token");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                clientToken = responseString;
            }
        });
    }
}
