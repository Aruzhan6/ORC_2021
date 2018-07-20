package com.example.meirlen.orc.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.base.BaseFragmentManagerActivity;
import com.example.meirlen.orc.view.fragment.SignInFragment;
import com.example.meirlen.orc.view.fragment.SignUpFragment;


public class SignInActivity extends BaseFragmentManagerActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fm_activity);
    }

    @Override
    protected Fragment fragment() {
        return SignInFragment.newInstance();
    }

    @Override
    protected String title() {
        return "Авторизация";
    }

}

