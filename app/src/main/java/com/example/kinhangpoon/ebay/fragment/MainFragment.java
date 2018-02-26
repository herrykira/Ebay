package com.example.kinhangpoon.ebay.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.adapter.MainAdapter;

/**
 * Created by KinhangPoon on 22/2/2018.
 */

public class MainFragment extends Fragment {
    Button ButtonLogin,ButtonRegister;
    FragmentSwitch fragmentSwitch;
    RecyclerView recyclerView;
    MainAdapter mainAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentSwitch = (FragmentSwitch) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment,container,false);
        ButtonLogin = view.findViewById(R.id.button_main_login);
        ButtonRegister = view.findViewById(R.id.button_main_register);
        recyclerView = view.findViewById(R.id.recyclerView_main);
        mainAdapter = new MainAdapter(getContext(),fragmentSwitch);
        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));

        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitch.switchToLogin();
            }
        });

        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitch.switchToRegister();
            }
        });
        return view;
    }

}
