package com.example.meirlen.orc.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.meirlen.orc.helper.GlobalVariables;
import com.example.meirlen.orc.helper.SessionManager;
import com.example.meirlen.orc.interfaces.OnCategoryClickListener;
import com.example.meirlen.orc.presenter.CategoryPresenter;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.view.CategoryView;
import com.example.meirlen.orc.view.activity.ChildCategoryActivity;
import com.example.meirlen.orc.view.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoriesFragment extends Fragment implements CategoryView, OnCategoryClickListener {


    private static final String TAG = "CategoriesFragment";

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    CategoryPresenter categoryPresenter;

    @Inject
    SessionManager sessionManager;

    private CategoryAdapter adapter;
    List<Category> list = new ArrayList<>();


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
        App.getInstance().createCategoryComponent().inject(this);
        Log.d(TAG, "onCreateView: " + sessionManager.getAccessToken());

        init();
        categoryPresenter.setView(this);
        categoryPresenter.getCategories(sessionManager.getAccessToken());
        //categoryPresenter.getCategoriesFromLocalDb();


        return rootView;
    }



    @Override
    public void getCategories(List<Category> categories) {
        list.addAll(categories);
        adapter.notifyDataSetChanged();
        categoryPresenter.insertLocalDb(categories);
        categoryPresenter.getCartCount(sessionManager.getAccessToken());
    }

    @Override
    public void getCardCount(String count) {
        GlobalVariables.COUNT_CART = Integer.parseInt(count);
        GlobalVariables.basketManager.update();
    }


    @Override
    public void successLocalDb(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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

        adapter = new CategoryAdapter(this, list, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


    }


    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(getContext(), ChildCategoryActivity.class);
        intent.putExtra(ChildCategoryActivity.EXTRA_NAME_PARENT_CATEGORY, list.get(pos).getCategoryName());
        intent.putExtra(ChildCategoryActivity.EXTRA_ID_PARENT_CATEGORY, String.valueOf(list.get(pos).getCategoryId()));
        startActivity(intent);
    }
}
