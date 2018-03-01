package com.example.kinhangpoon.ebay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.adapter.TaskPagerAdapter;

/**
 * Created by KinhangPoon on 25/2/2018.
 */

/**
 * show task fragment
 */
public class TaskFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    /**
     * declaration
     */
    TabLayout tabLayout;
    ViewPager viewPager;
    TaskPagerAdapter taskPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_fragment,container,false);
        /**
         * initialization
         */
        tabLayout = view.findViewById(R.id.tablayout_task);
        viewPager = view.findViewById(R.id.viewPager_task);

        tabLayout.addTab(tabLayout.newTab().setText("Category"));
        tabLayout.addTab(tabLayout.newTab().setText("Order History"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setOnTabSelectedListener(this);
/** Important: Must use the child FragmentManager or you will see side effects. */
        taskPagerAdapter = new TaskPagerAdapter(getChildFragmentManager(),2);
        viewPager.setAdapter(taskPagerAdapter);
        /**
         * set up viewpager
         */
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.setScrollPosition(position,0,true);
                tabLayout.setSelected(true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }
}
