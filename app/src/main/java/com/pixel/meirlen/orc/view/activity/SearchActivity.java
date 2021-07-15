package com.pixel.meirlen.orc.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.base.BaseFragmentManagerActivity;
import com.pixel.meirlen.orc.helper.ProductViewEnum;
import com.pixel.meirlen.orc.interfaces.FavouriteMethodCaller;
import com.pixel.meirlen.orc.interfaces.OnSearchListener;
import com.pixel.meirlen.orc.model.SearchValue;
import com.pixel.meirlen.orc.view.fragment.ProductFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchActivity extends BaseFragmentManagerActivity {

    Fragment fragment;
    @BindView(R.id.editText13)
    EditText editText;
    OnSearchListener searchListener;

    public static final String EXTRA_SEARCH_KEY = "extra:search_key";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        editText.setText(Objects.requireNonNull(getIntent().getStringExtra(EXTRA_SEARCH_KEY)));
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                searchInit();
            }
            return false;
        });


    }

    @Override
    protected Fragment fragment() {
        ProductFragment fragment = ProductFragment.newInstance(ProductViewEnum.SEARCH);
        setListener(fragment);
        return fragment;
    }

    @Override
    protected String title() {
        return getString(R.string.search);
    }

    public void setListener(OnSearchListener listener) {
        this.searchListener = listener;
    }


    @OnClick(R.id.r1)
    public void onViewClicked() {
        searchInit();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        this.fragment = fragment;
    }

    private void searchInit() {
        if (editText.getText().toString().trim().length() > 0) {
            searchListener.onSearchByName(editText.getText().toString().trim());
        } else {
            Toast.makeText(SearchActivity.this, "Пустое поле", Toast.LENGTH_SHORT).show();
        }
    }
}
