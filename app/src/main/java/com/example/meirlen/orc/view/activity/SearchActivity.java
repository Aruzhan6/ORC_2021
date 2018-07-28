package com.example.meirlen.orc.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.base.BaseFragmentManagerActivity;
import com.example.meirlen.orc.view.fragment.SearchFragment;


public class SearchActivity extends BaseFragmentManagerActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    }

    @Override
    protected Fragment fragment() {
        return new SearchFragment();
    }

    @Override
    protected String title() {
        return getString(R.string.search);
    }


}
