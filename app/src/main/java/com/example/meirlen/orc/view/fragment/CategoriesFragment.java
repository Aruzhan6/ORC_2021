package com.example.meirlen.orc.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.presenter.CategoryPresenter;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.view.CategoryView;
import com.example.meirlen.orc.view.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoriesFragment extends Fragment implements CategoryView {


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    CategoryPresenter categoryPresenter;


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
        View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, rootView);
        App.getInstance().createCategoryComponent().inject(this);

        init();
        categoryPresenter.setView(this);
        categoryPresenter.getCategories("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ODksInRpbWVzdGFtcCI6MTUzMTEzNzYwOC42MDI2MDh9.-XdJaLVB6xmwc0nbzm2_iXGlAKZXnrRgOvGy4b8D6-Q");
        //categoryPresenter.getCategoriesFromLocalDb();
        return rootView;
    }


    @Override
    public void getCategories(List<Category> categories) {
        list.addAll(categories);
        adapter.notifyDataSetChanged();
        categoryPresenter.insertLocalDb(categories);
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

        adapter = new CategoryAdapter(list, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


    }


}
