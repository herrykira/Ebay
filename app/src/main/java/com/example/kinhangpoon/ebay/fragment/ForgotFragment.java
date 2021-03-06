package com.example.kinhangpoon.ebay.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.network.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by KinhangPoon on 22/2/2018.
 */

/**
 * implement forgot password function
 */
public class ForgotFragment extends Fragment {
    /**
     * declaration
     */
    Button buttonGetPassword;
    TextView textViewRegister;
    TextView textViewContact;
    EditText editTextGetPassword;
    FragmentSwitch fragmentSwitch;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentSwitch = (FragmentSwitch) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forgot_fragment,container,false);
        /**
         * initialization
         */
        buttonGetPassword = view.findViewById(R.id.button_get_password);
        textViewRegister = view.findViewById(R.id.textView_forgot_register);
        textViewContact = view.findViewById(R.id.textView_forgot_contact);
        editTextGetPassword = view.findViewById(R.id.editText_get_password);

        buttonGetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = editTextGetPassword.getText().toString();
                /**
                 * make sure length of mobile is >=10
                 */
                if(mobile.length()<10){
                    editTextGetPassword.setError("Please enter correct mobile (length of mobile must be at least 10)");
                    editTextGetPassword.requestFocus();
                    return;
                }
                getPasswordMethod(mobile);
            }
        });

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitch.switchToRegister();
            }
        });

        return view;
    }

    public void getPasswordMethod(final String mobile){
        String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_fogot_pass.php?";
        String tag_json_obj = "json_obj_forgot";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("getPassword",response.toString());
                /**
                 * get data from json array by url
                 */
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject data = jsonArray.getJSONObject(0);
                    String password = data.getString("UserPassword");
                    if(password.equals("")){
                        Toast.makeText(getContext(),"Mobile is not registered",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getContext(),"Your password is "+password,Toast.LENGTH_LONG).show();
                    }
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
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest,tag_json_obj);
    }
}
