package com.pixel.meirlen.orc.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.base.BaseFragmentManagerActivity;
import com.pixel.meirlen.orc.view.fragment.AboutFragment;


public class AboutActivity extends BaseFragmentManagerActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fm_activity);

    }

    @Override
    protected Fragment fragment() {
        return new AboutFragment();
    }

    @Override
    protected String title() {
        return getIntent().getStringExtra("name");
    }


}
