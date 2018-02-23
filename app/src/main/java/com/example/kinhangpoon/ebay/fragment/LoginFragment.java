package com.example.kinhangpoon.ebay.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kinhangpoon.ebay.network.AppController;
import com.example.kinhangpoon.ebay.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by KinhangPoon on 21/2/2018.
 */

public class LoginFragment extends Fragment {

    EditText editTextLoginMobile,editTextLoginPassword;
    Button buttonLogin, buttonRegisterFor,buttonForgot;
    SharedPreferences sharedPreferences;
    FragmentSwitch fragmentSwitch;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentSwitch = (FragmentSwitch) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment,container,false);
        editTextLoginMobile = view.findViewById(R.id.editText_login_mobile);
        editTextLoginPassword = view.findViewById(R.id.editText_login_password);
        sharedPreferences = getActivity().getSharedPreferences("myinfo", Context.MODE_PRIVATE);
        buttonLogin = view.findViewById(R.id.button_login);
        buttonRegisterFor = view.findViewById(R.id.button_register_for);
        buttonForgot = view.findViewById(R.id.button_forgot);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = editTextLoginMobile.getText().toString();
                String password = editTextLoginPassword.getText().toString();
                loginRequestMethod(mobile,password);
            }
        });

        buttonRegisterFor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitch.switchToRegister();
            }
        });

        buttonForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitch.switchToForgot();
            }
        });


        return view;
    }

    public void loginRequestMethod(final String mobile, final String password){
        String url ="http://rjtmobile.com/ansari/shopingcart/androidapp/shop_login.php?";
//        mobile="+mobile+"&password="+password;
        String tag_json_obj = "json_obj_login";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response",response.toString());
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject data = jsonArray.getJSONObject(0);
                    String userId = data.getString("UserID");
                    Log.e("userId",userId);
                    String appApiKey = data.getString("AppApiKey ");
                    Log.e("api",appApiKey);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("UserID",userId);
                    editor.putString("AppApiKey",appApiKey);
                    editor.commit();

                    fragmentSwitch.switchToProductList();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("mobile",mobile);
                params.put("password",password);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }
}
