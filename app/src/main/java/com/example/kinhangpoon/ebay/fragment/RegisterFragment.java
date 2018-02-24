package com.example.kinhangpoon.ebay.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kinhangpoon.ebay.network.AppController;
import com.example.kinhangpoon.ebay.R;

import static com.example.kinhangpoon.ebay.network.AppController.TAG;

/**
 * Created by KinhangPoon on 21/2/2018.
 */

public class RegisterFragment extends Fragment {
    EditText editTextRegisterName,editTextRegisterMobile,editTextRegisterPassword,editTextRegisterEmail;
    Button buttonRegister;
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
        View view = inflater.inflate(R.layout.register_fragment,container,false);
        editTextRegisterEmail = view.findViewById(R.id.editText_register_email);
        editTextRegisterMobile = view.findViewById(R.id.editText_register_mobile);
        editTextRegisterName = view.findViewById(R.id.editText_register_name);
        editTextRegisterPassword = view.findViewById(R.id.editText_register_password);
        textViewResetRegister = view.findViewById(R.id.textView_reset_register);
        textViewResetContact = view.findViewById(R.id.textView_reset_contact);

        buttonRegister = view.findViewById(R.id.button_register);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


        textViewResetRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitch.switchToRegister();
            }
        });

        return view;
    }

    public void registerUser(){
        final String name = editTextRegisterName.getText().toString();
        final String mobile = editTextRegisterMobile.getText().toString();
        final String email = editTextRegisterEmail.getText().toString();
        final String password = editTextRegisterPassword.getText().toString();
        if(TextUtils.isEmpty(name)){
            editTextRegisterName.setError("Please enter username");
            editTextRegisterName.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextRegisterPassword.setError("Please enter correct password (length of password must be at least 6)");
            editTextRegisterPassword.requestFocus();
            return;
        }
        if(!email.contains("@")){
            editTextRegisterEmail.setError("Please enter correct Email Address");
            editTextRegisterEmail.requestFocus();
            return;
        }
        if(mobile.length()<10){
            editTextRegisterMobile.setError("Please enter correct mobile (length of mobile must be at least 10)");
            editTextRegisterMobile.requestFocus();
            return;
        }
        registerRequestMethod(name,mobile,email,password);
    }
    public void registerRequestMethod(final String name, final String mobile, final String email, final String password){
        String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_reg.php?"
                +"name="+name+"&email="+email+"&mobile="+mobile+"&password="+password;
        String tag_json_obj = "json_obj_req";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG,response.toString());
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
                if(!response.trim().toString().equals("Mobile Number already exsist")) {
                    fragmentSwitch.switchToLogin();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getContext(),"Fail to connect the server",Toast.LENGTH_LONG).show();
            }
        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                params.put("name",name);
//                params.put("email",email);
//                params.put("mobile",mobile);
//                params.put("password",password);
//                return params;
//            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        requestQueue.add(stringRequest);

    }
}
