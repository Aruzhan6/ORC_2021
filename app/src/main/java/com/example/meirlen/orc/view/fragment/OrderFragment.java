package com.example.meirlen.orc.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.SessionManager;
import com.example.meirlen.orc.presenter.OrderPresenter;
import com.example.meirlen.orc.view.OrderView;
import com.example.meirlen.orc.view.activity.ModalActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OrderFragment extends Fragment implements OrderView {
    BottomSheetDialog dialog;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @Inject
    OrderPresenter presenter;

    @Inject
    SessionManager sessionManager;


    @BindView(R.id.loading_bar)
    RelativeLayout progressLayout;

    @BindView(R.id.create)
    RelativeLayout createLayout;


    @BindView(R.id.line1)
    LinearLayout contentLayout;

    private static final String TAG = "BasketFragment";


    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, rootView);
        App.getInstance().createOrderComponent().inject(this);
        Log.d(TAG, "onCreateView: " + sessionManager.getAccessToken());
        presenter.setView(this);
        return rootView;
    }


    @Override
    public void showLoading() {

        progressLayout.setVisibility(View.VISIBLE);
        createLayout.setVisibility(View.GONE);
        contentLayout.setAlpha((float) 0.5);
    }

    @Override
    public void hideLoading() {

        progressLayout.setVisibility(View.GONE);
        createLayout.setVisibility(View.VISIBLE);
        contentLayout.setAlpha((float) 1.0);
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


    @Override
    public void isSussess(boolean success) {

        if (success) {
            Intent intent = new Intent(getContext(), ModalActivity.class);
            startActivity(intent);
        }


    }

    @OnClick(R.id.create)
    public void addOrder() {
        presenter.sendOrder(sessionManager.getAccessToken(), "-45.4545", "12.48945");
    }
}
