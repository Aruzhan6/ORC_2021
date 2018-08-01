package com.example.meirlen.orc.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meirlen.orc.R;
import com.example.meirlen.orc.base.BaseFragmentManagerActivity;
import com.example.meirlen.orc.helper.ProductViewEnum;
import com.example.meirlen.orc.interfaces.OnSearchListener;
import com.example.meirlen.orc.view.fragment.ProductFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchActivity extends BaseFragmentManagerActivity {

    Fragment fragment;
    @BindView(R.id.editText13)
    EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

    }

    @Override
    protected Fragment fragment() {
        return ProductFragment.newInstance(ProductViewEnum.SEARCH);
    }

    @Override
    protected String title() {
        return getString(R.string.search);
    }


    @OnClick(R.id.r1)
    public void onViewClicked() {
        ((OnSearchListener) fragment).onSearchByName(editText.getText().toString().trim());
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        this.fragment = fragment;
    }


}
