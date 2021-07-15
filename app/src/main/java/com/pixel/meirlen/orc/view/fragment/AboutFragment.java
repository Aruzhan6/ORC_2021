package com.pixel.meirlen.orc.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.pixel.meirlen.orc.R;

import butterknife.ButterKnife;


public class AboutFragment extends android.support.v4.app.Fragment {

    View rootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_about, null);
        ButterKnife.bind(this, rootview);
        return rootview;
    }
}
