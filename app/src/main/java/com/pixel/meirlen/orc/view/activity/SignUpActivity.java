package com.pixel.meirlen.orc.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.base.BaseFragmentManagerActivity;
import com.pixel.meirlen.orc.view.fragment.SignUpFragment;


public class SignUpActivity extends BaseFragmentManagerActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fm_activity);
    }

    @Override
    protected Fragment fragment() {
        return SignUpFragment.newInstance();
    }

    @Override
    protected String title() {
        return "Регистрация";
    }

}

