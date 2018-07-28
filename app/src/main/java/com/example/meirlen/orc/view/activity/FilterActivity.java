package com.example.meirlen.orc.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.BottomNavigationBehavior;
import com.example.meirlen.orc.interfaces.OnFieldItemSelectedListener;
import com.example.meirlen.orc.interfaces.OnValueClearIListener;
import com.example.meirlen.orc.model.Field;
import com.example.meirlen.orc.model.request.Filter;
import com.example.meirlen.orc.view.fragment.CategoriesFragment;
import com.example.meirlen.orc.view.fragment.FieldFragment;
import com.example.meirlen.orc.view.fragment.ProductFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity implements OnFieldItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    private int state = 0;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fm_activity);
        ButterKnife.bind(this);
        mToolbar.setTitle(R.string.filter);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_righ);
        });

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, FieldFragment.newInstance()).commit();

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        this.fragment = fragment;
    }

    @Override
    public void onFieldItemPicked(String title) {
        mToolbar.setTitle(title);
        state = 1;
        changeState(true);
    }


    private void changeState(boolean enable) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
            getSupportActionBar().setDisplayShowHomeEnabled(enable);
        }
    }

    @Override
    public void onBackPressed() {
        if (state == 1) {
            ((OnValueClearIListener) fragment).onValueClear();
            changeState(false);
            state = 0;
            mToolbar.setTitle(R.string.filter);
        } else {
            super.onBackPressed();
        }

    }
}
