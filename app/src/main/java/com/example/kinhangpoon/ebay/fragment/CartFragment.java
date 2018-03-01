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

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    String GET_TOKEN_URL = "http://rjtmobile.com/aamir/braintree-paypal-payment/main.php?";
    String SEND_PAYMENT_DETAIL_URL = "http://rjtmobile.com/aamir/braintree-paypal-payment/mycheckout.php?";
    String clientToken;
    static final int BRAIN_TREE_REQUEST_CODE = 4949;
    Map<String,String> paramHash;

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
        getClientTokenFromAppServer();
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

                    //paypal
                    onBrainTreeSubmit(v);


                }
                else{
                    fragmentSwitch.switchToLogin();
                }
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
                Log.i("payment_notice",payment_notice);
                int amount =0 ;
                for(Product product:Product.shoppingCart){
                    amount+=Integer.valueOf(product.getProductPrize());
                }
                Log.i("amount",amount+"");
                paramHash = new HashMap<>();
                paramHash.put("amount",amount+"");
                paramHash.put("nonce", payment_notice);

                sendToMerchant();

                Log.e("MerchantSuccessful","sucessful");
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
        asyncHttpClient.get(GET_TOKEN_URL, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("Token Failed","Failed Token");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                clientToken = responseString;
                Log.i("clientToken",clientToken);
            }
        });
    }
    public void sendToOrder(){
        List<Product> products = Product.shoppingCart;
        Log.i("productSize",products.size()+"");
        String productId="";
        String productName="";
        String productQuantity="0";
        String productPrice="0";
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            productId = productId + product.getProductId()+ ",";
            productName = productName + product.getProductName()+",";
            productQuantity = (Integer.valueOf(productQuantity) + Integer.valueOf(product.getProductQuantity()))+"";
            productPrice = Double.valueOf(productPrice) + Double.valueOf(product.getProductPrize()) + "";
        }
        String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/orders.php?&item_id="+productId+"&item_names="+productName+"&item_quantity="+productQuantity+
                    "&final_price="+productPrice+"&mobile="+mobile+"&api_key="+appApiKey+"&user_id="+userId;
        String tag_json_obj = "json_obj_order";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("orderResponse",response.toString());
                Toast.makeText(getContext(),"Successfully order",Toast.LENGTH_SHORT).show();
            }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        AppController.getInstance().addToRequestQueue(stringRequest,tag_json_obj);

        Product.shoppingCart = new ArrayList<>();
        fragmentSwitch.switchToCategory();
    }
    public void sendToMerchant(){
        String tag_json_obj = "json_obj_merchant";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_PAYMENT_DETAIL_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if(response.contains("Successful")){
                    Toast.makeText(getContext(),"Your transaction successful",Toast.LENGTH_SHORT).show();
                    Log.i("Merchant_response",response);
                    sendToOrder();
                }
                else{
                    Toast.makeText(getContext(),"Your transaction failed",Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Merchant_error",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return paramHash;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest,tag_json_obj);
    }
}
