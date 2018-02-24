package com.example.kinhangpoon.ebay.fragment;

/**
 * Created by KinhangPoon on 22/2/2018.
 */

public interface FragmentSwitch {
    public void switchToLogin();
    public void switchToRegister();
    public void switchToCategory();
    public void switchToSubCategory(String id);
    public void switchToForgot();
    public void switchToReset();
    public void switchToDetail(String id);
}