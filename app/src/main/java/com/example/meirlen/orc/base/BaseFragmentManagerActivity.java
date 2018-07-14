package com.example.meirlen.orc.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.meirlen.orc.R;


public abstract class BaseFragmentManagerActivity extends AppCompatActivity {


    private Toolbar mToolbar;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initToolbar();
        initmFragmentManager();

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setTitle(title());
     //   mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorHint));
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_righ);
        });
    }

    private void initmFragmentManager() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,fragment()).commit();
    }



    protected abstract Fragment fragment();
    protected abstract String title();



}