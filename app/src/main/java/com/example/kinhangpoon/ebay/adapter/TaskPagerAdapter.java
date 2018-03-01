package com.example.kinhangpoon.ebay.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kinhangpoon.ebay.fragment.CategoryFragment;
import com.example.kinhangpoon.ebay.fragment.OrderHistoryFragment;

/**
 * Created by KinhangPoon on 25/2/2018.
 */

/**
 * Adapter for viewpager in task fragment
 */
public class TaskPagerAdapter extends FragmentPagerAdapter {
    /**
     * number of tabs
     */
    int tabcount;

    /**
     * constructor for viewpager adapter
     * @param fm
     * @param tabcount
     */
    public TaskPagerAdapter(FragmentManager fm, int tabcount) {
        super(fm);
        this.tabcount = tabcount;
    }

    /**
     * different tabs for different fragments
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                CategoryFragment categoryFragment = new CategoryFragment();
                return categoryFragment;
            case 1:
                OrderHistoryFragment orderHistoryFragment = new OrderHistoryFragment();
                return orderHistoryFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
