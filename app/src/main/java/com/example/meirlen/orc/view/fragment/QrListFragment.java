package com.example.meirlen.orc.view.fragment;

import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.SessionManager;

import com.example.meirlen.orc.model.qr.QR;
import com.example.meirlen.orc.presenter.QrPresenter;
import com.example.meirlen.orc.view.QrView;
import com.example.meirlen.orc.view.activity.DecoderActivity;
import com.example.meirlen.orc.view.adapter.QrAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class QrListFragment extends Fragment implements QrView {
    BottomSheetDialog dialog;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<QR> list = new ArrayList<>();

    @Inject
    QrPresenter presenter;

    @Inject
    SessionManager sessionManager;


    private QrAdapter adapter;

    @BindView(R.id.hintLayout)
    LinearLayout hintLayout;

    private static final String TAG = "BasketFragment";


    public static QrListFragment newInstance() {
        return new QrListFragment();
    }

    public static QrListFragment newInstance(String param1, String param2) {
        QrListFragment fragment = new QrListFragment();
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
        App.getInstance().createQrComponent().inject(this);
        presenter.setView(this);
        init();
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
        adapter = new QrAdapter(list, getActivity());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getList(List<QR> products) {
        Log.d(TAG, "getHistory: " + products.size());
        list.clear();
        list.addAll(products);
        adapter.notifyDataSetChanged();


        if (products.size() > 0) {
            hintLayout.setVisibility(View.GONE);
        } else {
            hintLayout.setVisibility(View.VISIBLE);

        }
    }

    @OnClick(R.id.qr_code)
    public void qrApp() {
        Intent i = new Intent(getContext(), DecoderActivity.class);
        startActivity(i);
        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_righ);

    }

}
