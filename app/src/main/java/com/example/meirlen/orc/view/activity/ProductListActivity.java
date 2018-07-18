package com.example.meirlen.orc.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.base.BaseFragmentManagerActivity;
import com.example.meirlen.orc.view.fragment.ChildCategoriesFragment;
import com.example.meirlen.orc.view.fragment.ProductFragment;


public class ProductListActivity extends BaseFragmentManagerActivity {

    public static final String EXTRA_ID_PARENT_CATEGORY = "extra:parent_id";
    public static final String EXTRA_NAME_PARENT_CATEGORY = "extra:login";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);



    }

    @Override
    protected Fragment fragment() {
        return ProductFragment.newInstance();
    }

    @Override
    protected String title() {
       // return getIntent().getStringExtra(EXTRA_NAME_PARENT_CATEGORY);
        return "Название продукта";
    }

}
