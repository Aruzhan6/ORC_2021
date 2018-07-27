package com.example.meirlen.orc.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.base.BaseFragmentManagerActivity;
import com.example.meirlen.orc.view.fragment.AboutFragment;
import com.example.meirlen.orc.view.fragment.OrderFragment;


public class OrderActivity extends BaseFragmentManagerActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fm_activity);

    }

    @Override
    protected Fragment fragment() {
        return new OrderFragment();
    }

    @Override
    protected String title() {
        return "Оформление заказа";
    }


}
