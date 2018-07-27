package com.example.meirlen.orc.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.SessionManager;
import com.example.meirlen.orc.interfaces.OnAddCardListener;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.basket.Basket;
import com.example.meirlen.orc.model.history.History;
import com.example.meirlen.orc.presenter.BasketPresenter;
import com.example.meirlen.orc.view.BasketView;
import com.example.meirlen.orc.view.adapter.BasketAdapter;
import com.example.meirlen.orc.view.adapter.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HistoryFragment extends Fragment implements BasketView {
    BottomSheetDialog dialog;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    BasketPresenter presenter;

    @Inject
    SessionManager sessionManager;


    private HistoryAdapter adapter;
    List<History> list = new ArrayList<>();
    private int posiition;


    private static final String TAG = "BasketFragment";


    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, rootView);
        App.getInstance().createBasketComponent().inject(this);

        Log.d(TAG, "onCreateView: " + sessionManager.getAccessToken());

        init();
        presenter.setView(this);
        presenter.getHistory(sessionManager.getAccessToken());

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
        adapter = new HistoryAdapter(list, getActivity());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void getList(List<Basket> products) {
        Log.d(TAG, "getList: " + products.size());

    }

    @Override
    public void getHistory(List<History> histories) {
        Log.d(TAG, "getHistory: " + histories.size());
        list.clear();
        list.addAll(histories);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void addCartResponse(CardResponse response) {

    }


    @Override
    public void showItemLoading() {

    }

    @Override
    public void hideItemLoading() {

    }
}
