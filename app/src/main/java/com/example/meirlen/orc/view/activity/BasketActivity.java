package com.example.meirlen.orc.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.base.BaseFragmentManagerActivity;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.view.fragment.BasketFragment;
import com.google.gson.Gson;

import static com.example.meirlen.orc.view.activity.DetailActivity.EXTRA_PRODUCT;
import static com.example.meirlen.orc.view.fragment.ProductFragment.EXTRA_UPDATE_PRODUCT;


public class BasketActivity extends BaseFragmentManagerActivity {

    public static final String EXTRA_UPDATE = "extra:update";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fm_activity);
    }

    @Override
    protected Fragment fragment() {
        return BasketFragment.newInstance();
    }

    @Override
    protected String title() {
        return "Корзина";
    }


    static public Intent basketIntent(Activity context,String update) {
        Intent intent = new Intent(context, BasketActivity.class);
        intent.putExtra(EXTRA_UPDATE, update);
        return intent;
    }

    @Override
    public void onBackPressed() {
        if (getIntent().getStringExtra(EXTRA_UPDATE) != null) {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK,returnIntent);
            finish();

        } else {
            super.onBackPressed();
        }
    }

}

