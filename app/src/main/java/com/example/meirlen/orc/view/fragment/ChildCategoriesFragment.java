package com.example.meirlen.orc.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.meirlen.orc.interactor.CategoryInteractor;
import com.example.meirlen.orc.presenter.CategoryPresenter;
import com.example.meirlen.orc.rest.model.Category;
import com.example.meirlen.orc.view.CategoryView;
import com.example.meirlen.orc.view.activity.ChildCategoryActivity;
import com.example.meirlen.orc.view.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChildCategoriesFragment extends Fragment implements CategoryView {


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    CategoryPresenter categoryPresenter;

    @Inject
    CategoryInteractor categoryInteractor;

    private CategoryAdapter adapter;
    List<Category> list = new ArrayList<>();


    public static ChildCategoriesFragment newInstance() {
        return new ChildCategoriesFragment();
    }

    public static ChildCategoriesFragment newInstance(String param1, String param2) {
        ChildCategoriesFragment fragment = new ChildCategoriesFragment();
        Bundle args = new Bundle();
        args.putString(ChildCategoryActivity.EXTRA_NAME_PARENT_CATEGORY, param1);
        args.putString(ChildCategoryActivity.EXTRA_ID_PARENT_CATEGORY, param2);
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, rootView);
        App.getInstance().createChatComponent().inject(this);
        init();
        categoryPresenter.setView(this);
        assert getArguments() != null;
        categoryPresenter.getCategoriesById(getArguments().getString(ChildCategoryActivity.EXTRA_ID_PARENT_CATEGORY));
        return rootView;
    }


    @Override
    public void getCategories(List<Category> categories) {
        list.addAll(categories);
        adapter.notifyDataSetChanged();

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
