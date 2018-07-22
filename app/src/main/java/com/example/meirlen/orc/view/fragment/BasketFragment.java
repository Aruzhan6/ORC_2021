package com.example.meirlen.orc.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.SessionManager;
import com.example.meirlen.orc.interfaces.OnAddCardListener;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.basket.Basket;
import com.example.meirlen.orc.model.filter.BooleanType;
import com.example.meirlen.orc.model.filter.MultipleSelect;
import com.example.meirlen.orc.model.filter.RangeInt;
import com.example.meirlen.orc.model.filter.StringType;
import com.example.meirlen.orc.model.request.Filter;
import com.example.meirlen.orc.presenter.BasketPresenter;
import com.example.meirlen.orc.presenter.ProductPresenter;
import com.example.meirlen.orc.view.BasketView;
import com.example.meirlen.orc.view.ProductView;
import com.example.meirlen.orc.view.activity.BasketActivity;
import com.example.meirlen.orc.view.activity.FilterActivity;
import com.example.meirlen.orc.view.adapter.BasketAdapter;
import com.example.meirlen.orc.view.adapter.ProductAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BasketFragment extends Fragment implements BasketView, OnAddCardListener {
    BottomSheetDialog dialog;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    BasketPresenter presenter;

    @Inject
    SessionManager sessionManager;


    private BasketAdapter adapter;
    List<Basket> list = new ArrayList<>();
    private int posiition;


    private static final String TAG = "BasketFragment";


    public static BasketFragment newInstance() {
        return new BasketFragment();
    }

    public static BasketFragment newInstance(String param1, String param2) {
        BasketFragment fragment = new BasketFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_basket, container, false);
        ButterKnife.bind(this, rootView);
        App.getInstance().createBasketComponent().inject(this);

        Log.d(TAG, "onCreateView: " + sessionManager.getAccessToken());

        init();
        presenter.setView(this);
        presenter.getList(sessionManager.getAccessToken());

        return rootView;
    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loadingFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void init() {

        adapter = new BasketAdapter(this, list, getActivity());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


    }


    @Override
    public void getList(List<Basket> products) {
        Log.d(TAG, "getList: " + products.size());

        list.clear();
        list.addAll(products);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAddCard(String id, String decrement, int position) {

        this.posiition = position;
        presenter.addCart(sessionManager.getAccessToken(), id, decrement);
    }


    @Override
    public void addCartResponse(CardResponse response) {

        Basket basket = list.get(posiition);
        basket.setCartCount(String.valueOf(response.getCartCount()));
        basket.setTotalPrice(response.getTotalPrice());
        basket.setCartId(response.getCartId());
        basket.getProduct().setCartProductId(response.getCartProductId());
        list.set(posiition, basket);
        adapter.notifyDataSetChanged();


    }


    @Override
    public void showItemLoading() {
        adapter.showLoading();
    }

    @Override
    public void hideItemLoading() {
        adapter.hideLoading();

    }
}
