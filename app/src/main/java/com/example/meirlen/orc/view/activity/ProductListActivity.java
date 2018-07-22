package com.example.meirlen.orc.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.base.BaseFragmentManagerActivity;
import com.example.meirlen.orc.view.fragment.ProductFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class ProductListActivity extends BaseFragmentManagerActivity {

    public static final String EXTRA_ID_PARENT_CATEGORY = "extra:parent_id";
    public static final String EXTRA_NAME_PARENT_CATEGORY = "extra:login";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);

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

    @OnClick(R.id.action_bar_bt_trash)
    public void onViewClicked() {

        Intent intent = new Intent(this, BasketActivity.class);
        startActivity(intent);
    }
}
