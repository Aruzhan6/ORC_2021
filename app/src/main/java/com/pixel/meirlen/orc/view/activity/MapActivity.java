package com.pixel.meirlen.orc.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.base.BaseFragmentManagerActivity;
import com.pixel.meirlen.orc.view.fragment.AboutFragment;
import com.pixel.meirlen.orc.view.fragment.MyMapFragment;


public class MapActivity extends BaseFragmentManagerActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fm_activity);

    }

    @Override
    protected Fragment fragment() {
        return new MyMapFragment();
    }

    @Override
    protected String title() {
        return getString(R.string.current_location);
    }


}
