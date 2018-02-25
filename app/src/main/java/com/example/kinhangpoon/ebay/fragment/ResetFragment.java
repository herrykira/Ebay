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

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KinhangPoon on 23/2/2018.
 */

public class ResetFragment extends Fragment {
    EditText editTextResetMobile,editTextResetOld,editTextResetNew;
    Button buttonReset;
    FragmentSwitch fragmentSwitch;
    TextView textViewResetRegister,textViewResetContact;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentSwitch = (FragmentSwitch) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reset_fragment,container,false);
        editTextResetNew = view.findViewById(R.id.editText_retset_new);
        editTextResetOld = view.findViewById(R.id.editText_reset_old);
        editTextResetMobile = view.findViewById(R.id.editText_reset_mobile);
        textViewResetRegister = view.findViewById(R.id.textView_reset_register);
        textViewResetContact = view.findViewById(R.id.textView_reset_contact);
        buttonReset = view.findViewById(R.id.button_retset_password);

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = editTextResetMobile.getText().toString();
                String oldPassword = editTextResetOld.getText().toString();
                String newPassword = editTextResetNew.getText().toString();

                if(mobile.length()<10){
                    editTextResetMobile.setError("Please enter correct mobile (length of mobile must be at least 10)");
                    editTextResetMobile.requestFocus();
                    return;
                }

                if(oldPassword.length()<6){
                    editTextResetOld.setError("Please enter correct password (length of password must be at least 6)");
                    editTextResetOld.requestFocus();
                    return;
                }
                if(newPassword.length()<6){
                    editTextResetNew.setError("Please enter correct password (length of password must be at least 6)");
                    editTextResetNew.requestFocus();
                    return;
                }
                resetRequstMethod(mobile,oldPassword,newPassword);
            }
        });

        textViewResetRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitch.switchToRegister();
            }
        });
        textViewResetContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    public void resetRequstMethod(final String mobile, final String oldPassword, final String newPassword){
        String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_reset_pass.php?&mobile="+mobile+"&password="+oldPassword+"&newpassword="+newPassword;
//        String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_reset_pass.php?";
        Log.e("reset",url);
        String tag_json_obj = "json_obj_reset";
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String error="";
                Log.e("reset",response.toString());
                if(response.trim().contains("successfully")){
                    Toast.makeText(getContext(),"Reset Password Successfully",Toast.LENGTH_LONG).show();
                    fragmentSwitch.switchToLogin();
                }
                else {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("msg");
                        error = jsonArray.getString(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                params.put("mobile",mobile);
//                params.put("password",oldPassword);
//                params.put("newpassword",newPassword);
//                return params;
//            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest,tag_json_obj);
    }
}
