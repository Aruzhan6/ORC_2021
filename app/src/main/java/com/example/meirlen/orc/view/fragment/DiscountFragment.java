package com.example.meirlen.orc.view.fragment;

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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.SessionManager;

import com.example.meirlen.orc.model.discount.Discount;

import com.example.meirlen.orc.presenter.DiscountPresenter;
import com.example.meirlen.orc.view.DiscountView;
import com.example.meirlen.orc.view.adapter.DiscountAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DiscountFragment extends Fragment implements DiscountView {
    BottomSheetDialog dialog;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    DiscountPresenter presenter;

    @Inject
    SessionManager sessionManager;


    private DiscountAdapter adapter;
    List<Discount> list = new ArrayList<>();


    private static final String TAG = "DiscountFragment";


    public static DiscountFragment newInstance() {
        return new DiscountFragment();
    }

    public static DiscountFragment newInstance(String param1, String param2) {
        DiscountFragment fragment = new DiscountFragment();
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
        App.getInstance().createDiscountComponent().inject(this);

        Log.d(TAG, "onCreateView: " + sessionManager.getAccessToken());

        init();
        presenter.setView(this);
        presenter.getList();

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
        adapter = new DiscountAdapter(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override public int getSpanSize(int position) {
                return (position < 4) ? 2 : 1;
            }
        });

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void getList(List<Discount> products) {
        Log.d(TAG, "getList: " + products.size());
        adapter.addCakes(products);

    }


}
