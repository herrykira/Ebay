package com.example.kinhangpoon.ebay.fragment;

/**
 * Created by KinhangPoon on 22/2/2018.
 */

public interface FragmentSwitch {
    public void switchToLogin();
    public void switchToRegister();
    public void switchToProductList();
    public void switchToProductDetail(int position);
    public void switchToForgot();
}
