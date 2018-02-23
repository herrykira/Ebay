package com.example.kinhangpoon.ebay.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kinhangpoon.ebay.fragment.DetailFragment;
import com.example.kinhangpoon.ebay.fragment.ForgotFragment;
import com.example.kinhangpoon.ebay.fragment.FragmentSwitch;
import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.fragment.LoginFragment;
import com.example.kinhangpoon.ebay.fragment.MainFragment;
import com.example.kinhangpoon.ebay.fragment.ProductCategoryListFragment;
import com.example.kinhangpoon.ebay.fragment.RegisterFragment;

public class MainActivity extends AppCompatActivity implements FragmentSwitch {
//    RecyclerView recyclerView;
//    MainAdapter mainAdapter;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("myinfo", Context.MODE_PRIVATE);

//        recyclerView = findViewById(R.id.recyclerview_main);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,LinearLayoutManager.VERTICAL));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        mainAdapter = new MainAdapter();
//        recyclerView.setAdapter(mainAdapter);
        getSupportFragmentManager().beginTransaction().replace(R.id.main,new MainFragment()).addToBackStack(null).commit();

    }

    @Override
    public void switchToLogin() {
        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main,loginFragment).addToBackStack(null).commit();
    }

    @Override
    public void switchToRegister() {
        RegisterFragment registerFragment = new RegisterFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main,registerFragment).addToBackStack(null).commit();
    }

    @Override
    public void switchToProductList() {
        ProductCategoryListFragment productCategoryListFragment = new ProductCategoryListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main,productCategoryListFragment).addToBackStack(null).commit();
    }

    @Override
    public void switchToProductDetail(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.main,detailFragment).addToBackStack(null).commit();
    }

    @Override
    public void switchToForgot() {
        ForgotFragment forgotFragment = new ForgotFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main,forgotFragment).addToBackStack(null).commit();
    }
}
