package com.example.meirlen.orc.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.TextView;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.base.BaseFragmentManagerActivity;
import com.example.meirlen.orc.basket.Publisher;
import com.example.meirlen.orc.helper.GlobalVariables;
import com.example.meirlen.orc.view.fragment.ProductFragment;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ProductListActivity extends BaseFragmentManagerActivity implements Observer {

    public static final String EXTRA_ID_PARENT_CATEGORY = "extra:parent_id";
    public static final String EXTRA_NAME_PARENT_CATEGORY = "extra:login";
    private static final String TAG = "ProductListActivity";
    @BindView(R.id.txt_trash_count)
    TextView txtTrashCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        GlobalVariables.basketManager.register(this);
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

    @Override
    protected void onResume() {
        super.onResume();
        setTxtTrashCount();

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Publisher) {
            setTxtTrashCount();
        }
    }

    private void setTxtTrashCount() {
        txtTrashCount.setText(String.valueOf(GlobalVariables.COUNT_CART));

    }
}
