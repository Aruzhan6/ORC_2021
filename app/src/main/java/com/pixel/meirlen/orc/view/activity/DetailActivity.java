package com.pixel.meirlen.orc.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.base.BaseFragmentManagerActivity;
import com.pixel.meirlen.orc.interfaces.FavouriteMethodCaller;
import com.pixel.meirlen.orc.interfaces.UpdateProductListener;
import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.view.fragment.DetailFragment;
import com.google.gson.Gson;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pixel.meirlen.orc.view.fragment.ProductFragment.EXTRA_UPDATE_PRODUCT;


public class DetailActivity extends BaseFragmentManagerActivity implements UpdateProductListener {


    public static final String EXTRA_PRODUCT = "extra:product";
    public static final String EXTRA_PRODUCT_NAME = "extra:product_name";
    @BindView(R.id.imgViewFav)
    ImageView imgViewFav;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private Product product;
    private Gson gson = new Gson();

    private FavouriteMethodCaller listener;

    private boolean updateProduct = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fm_activity);
        ButterKnife.bind(this);
        imgViewFav.setVisibility(View.VISIBLE);


        String strObj = Objects.requireNonNull(getIntent().getStringExtra(EXTRA_PRODUCT));
        product = gson.fromJson(strObj, Product.class);
        drawFavIcon(product);



    }



    @OnClick(R.id.imgViewFav)
    public void favClick() {
        listener.markSpaceFavourie(product.getProductId());
    }

    @Override
    protected Fragment fragment() {
        DetailFragment fragment = DetailFragment.newInstance();
        setListener(fragment);
        return fragment;
    }


    public void setListener(FavouriteMethodCaller listener) {
        this.listener = listener;
    }


    @Override
    protected String title() {
        return getIntent().getStringExtra(EXTRA_PRODUCT_NAME);
    }


    static public Intent detailProductIntent(Activity context, Product product) {
        Gson gson = new Gson();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT, gson.toJson(product));
        intent.putExtra(EXTRA_PRODUCT_NAME, product.getProductName());
        return intent;
    }

    @Override
    public void onBackPressed() {
        if (updateProduct) {
            Intent returnIntent = new Intent();
            String myJson = gson.toJson(product);
            returnIntent.putExtra(EXTRA_UPDATE_PRODUCT, myJson);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onUpdateProduct(Product product) {
        this.product = product;
        updateProduct = true;
        drawFavIcon(product);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        imgViewFav.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        imgViewFav.setVisibility(View.VISIBLE);
    }
    private void drawFavIcon(Product product){
        if (product.getFavourite())
            imgViewFav.setImageDrawable(
                    getDrawable(R.drawable.ic_like));
        else
            imgViewFav.setImageDrawable(
                    getDrawable(R.drawable.ic_dislike));
    }

}
