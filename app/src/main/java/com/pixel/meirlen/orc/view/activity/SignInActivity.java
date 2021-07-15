package com.pixel.meirlen.orc.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.base.BaseFragmentManagerActivity;
import com.pixel.meirlen.orc.view.fragment.SignInFragment;
import com.pixel.meirlen.orc.view.fragment.SignUpFragment;


public class SignInActivity extends AppCompatActivity {

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new SignInFragment()).commit();
    }
}

