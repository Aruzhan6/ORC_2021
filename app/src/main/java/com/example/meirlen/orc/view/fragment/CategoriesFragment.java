package com.example.meirlen.orc.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.interactor.ChatInteractor;
import com.example.meirlen.orc.presenter.ChatPresenter;
import com.example.meirlen.orc.rest.model.Category;
import com.example.meirlen.orc.view.ChatView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class CategoriesFragment extends Fragment implements ChatView {


    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @Inject
    ChatPresenter chatPresenter;

    @Inject
    ChatInteractor chatInteractor;

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, rootView);
        App.getInstance().createChatComponent().inject(this);
        chatPresenter.setView(this);
        chatPresenter.getMessages("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ODksInRpbWVzdGFtcCI6MTUzMTEzNzYwOC42MDI2MDh9.-XdJaLVB6xmwc0nbzm2_iXGlAKZXnrRgOvGy4b8D6-Q");
        return rootView;
    }


    @Override
    public void getChats(List<Category> messages) {
        Toast.makeText(getContext(), "Количество обьектов: " + String.valueOf(messages.size()), Toast.LENGTH_SHORT).show();
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


}
