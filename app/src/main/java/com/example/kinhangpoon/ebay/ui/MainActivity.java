package com.example.kinhangpoon.ebay.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kinhangpoon.ebay.fragment.CartFragment;
import com.example.kinhangpoon.ebay.fragment.OrderHistoryFragment;
import com.example.kinhangpoon.ebay.fragment.ProductListFragment;
import com.example.kinhangpoon.ebay.fragment.SubCategoryFragment;
import com.example.kinhangpoon.ebay.fragment.ForgotFragment;
import com.example.kinhangpoon.ebay.fragment.FragmentSwitch;
import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.fragment.LoginFragment;
import com.example.kinhangpoon.ebay.fragment.MainFragment;
import com.example.kinhangpoon.ebay.fragment.CategoryFragment;
import com.example.kinhangpoon.ebay.fragment.RegisterFragment;
import com.example.kinhangpoon.ebay.fragment.ResetFragment;
import com.example.kinhangpoon.ebay.fragment.TaskFragment;

public class MainActivity extends AppCompatActivity implements FragmentSwitch {
//    RecyclerView recyclerView;
//    MainAdapter mainAdapter;
    SharedPreferences sharedPreferences;
    String userId,appApiKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * set up sharepreference
         */
        sharedPreferences = getSharedPreferences("myinfo", Context.MODE_PRIVATE);

//        recyclerView = findViewById(R.id.recyclerview_main);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,LinearLayoutManager.VERTICAL));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        mainAdapter = new MainAdapter();
//        recyclerView.setAdapter(mainAdapter);
         userId = sharedPreferences.getString("UserID","");
         appApiKey = sharedPreferences.getString("AppApiKey","");
        Log.i("start",userId);
        Log.i("start",appApiKey);
        if(userId.equals("")|| appApiKey.equals("")) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main, new MainFragment())
                    .addToBackStack(null).commit();
        }
        else{
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main,new TaskFragment())
                    .addToBackStack(null).commit();
        }
    }

    /**
     * set up menu for log in, log out, shopping cart, order history
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        userId = sharedPreferences.getString("UserID","");
        appApiKey = sharedPreferences.getString("AppApiKey","");
        if(userId.equals("")|| appApiKey.equals("")){
            getMenuInflater().inflate(R.menu.loginmenu,menu);
        }
        else{
            getMenuInflater().inflate(R.menu.logoutmenu,menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.logout){
            sharedPreferences = getSharedPreferences("myinfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("UserID","");
            editor.putString("AppApiKey","");
            editor.putString("Mobile","");

            editor.commit();
            userId = sharedPreferences.getString("UserID","");
            appApiKey = sharedPreferences.getString("AppApiKey","");
            Log.i("menu",userId);
            Log.i("menu",appApiKey);
//            getSupportFragmentManager().beginTransaction().replace(R.id.main,new MainFragment()).addToBackStack(null).commit();
            finish();
            startActivity(getIntent());
        }
        else if(id == R.id.login){
            getSupportFragmentManager().beginTransaction().replace(R.id.main,new LoginFragment()).addToBackStack(null).commit();
        }
        else if(id ==R.id.shoppingCart){
            getSupportFragmentManager().beginTransaction().replace(R.id.main,new CartFragment()).addToBackStack(null).commit();
        }
        else {
            getSupportFragmentManager().beginTransaction().replace(R.id.main,new OrderHistoryFragment()).addToBackStack(null).commit();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * implementation of the interface
     */
    @Override
    public void switchToMain() {
        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main,mainFragment)
                .commit();
    }

    @Override
    public void switchToTask() {
        TaskFragment taskFragment = new TaskFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main,taskFragment)
                .commit();
    }

    @Override
    public void switchToLogin() {
        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main,loginFragment)
                .commit();
    }

    @Override
    public void switchToRegister() {
        RegisterFragment registerFragment = new RegisterFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main,registerFragment)
                .commit();
    }

    @Override
    public void switchToCategory() {
        CategoryFragment categoryFragment = new CategoryFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main,categoryFragment)
                .commit();
    }

    @Override
    public void switchToSubCategory(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("CategoryId",id);
        SubCategoryFragment subCategoryFragment = new SubCategoryFragment();
        subCategoryFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main,subCategoryFragment)
                .commit();
    }

    @Override
    public void switchToForgot() {
        ForgotFragment forgotFragment = new ForgotFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main,forgotFragment)
                .commit();
    }

    @Override
    public void switchToReset() {
        ResetFragment resetFragment = new ResetFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main,resetFragment)
                .commit();
    }

    @Override
    public void switchToDetail(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("SubCategoryId",id);
        ProductListFragment productListFragment = new ProductListFragment();
        productListFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main,productListFragment)
                .commit();
    }

    @Override
    public void switchToCart() {
        CartFragment cartFragment = new CartFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main,cartFragment)
                .commit();
    }
}
