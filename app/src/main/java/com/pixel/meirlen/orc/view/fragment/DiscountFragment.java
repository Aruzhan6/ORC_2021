package com.pixel.meirlen.orc.view.fragment;

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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pixel.meirlen.orc.App;
import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.helper.SessionManager;
import com.pixel.meirlen.orc.model.discount.Discount;
import com.pixel.meirlen.orc.presenter.DiscountPresenter;
import com.pixel.meirlen.orc.view.DiscountView;
import com.pixel.meirlen.orc.view.activity.SearchActivity;
import com.pixel.meirlen.orc.view.adapter.DiscountAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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


    @BindView(R.id.editText13)
    EditText editText;

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
        View rootView = inflater.inflate(R.layout.fragment_discount, container, false);
        ButterKnife.bind(this, rootView);
        App.getInstance().createDiscountComponent().inject(this);

        Log.d(TAG, "onCreateView: " + sessionManager.getAccessToken());

        init();
        presenter.setView(this);
        presenter.getList();


        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                searchInit();
            }
            return false;
        });
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        /*GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position < 4) ? 2 : 1;
            }
        });*/
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void getList(List<Discount> products) {
        Log.d(TAG, "getList: " + products.size());
        adapter.addCakes(products);

    }


    @OnClick(R.id.imageView23)
    public void onViewClicked() {
        searchInit();
    }

    private void searchInit() {

        Intent intent = new Intent(getContext(), SearchActivity.class);
        if (editText.getText().toString().trim().length() > 0) {
            intent.putExtra(SearchActivity.EXTRA_SEARCH_KEY, editText.getText().toString().trim());
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Пустое поле", Toast.LENGTH_SHORT).show();
        }
    }

}
