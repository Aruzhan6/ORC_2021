package com.example.meirlen.orc.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.base.BaseFragmentManagerActivity;
import com.example.meirlen.orc.view.fragment.CategoriesFragment;
import com.example.meirlen.orc.view.fragment.ChildCategoriesFragment;


public class ChildCategoryActivity extends BaseFragmentManagerActivity {

    public static final String EXTRA_ID_PARENT_CATEGORY = "extra:parent_id";
    public static final String EXTRA_NAME_PARENT_CATEGORY = "extra:login";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fm_activity);



    }

    @Override
    protected Fragment fragment() {
        return ChildCategoriesFragment.newInstance(getIntent().getStringExtra(EXTRA_NAME_PARENT_CATEGORY), getIntent().getStringExtra(EXTRA_ID_PARENT_CATEGORY));
    }

    @Override
    protected String title() {
        return getIntent().getStringExtra(EXTRA_NAME_PARENT_CATEGORY);
    }

}
