package com.example.meirlen.orc.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.base.BaseFragmentManagerActivity;
import com.example.meirlen.orc.model.basket.Basket;
import com.example.meirlen.orc.view.fragment.BasketFragment;
import com.example.meirlen.orc.view.fragment.SignInFragment;


public class BasketActivity extends BaseFragmentManagerActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fm_activity);
    }

    @Override
    protected Fragment fragment() {
        return BasketFragment.newInstance();
    }

    @Override
    protected String title() {
        return "Корзина";
    }

}

