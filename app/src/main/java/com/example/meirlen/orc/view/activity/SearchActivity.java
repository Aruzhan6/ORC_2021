package com.example.meirlen.orc.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.base.BaseFragmentManagerActivity;
import com.example.meirlen.orc.view.fragment.SearchFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchActivity extends BaseFragmentManagerActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

    }

    @Override
    protected Fragment fragment() {
        return new SearchFragment();
    }

    @Override
    protected String title() {
        return getString(R.string.search);
    }


    @OnClick(R.id.imageView23)
    public void onViewClicked() {
        Intent intent = new Intent(this, ProductListActivity.class);
        startActivity(intent);         
    }
}
