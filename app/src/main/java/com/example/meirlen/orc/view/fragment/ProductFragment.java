package com.example.meirlen.orc.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.filter.BooleanType;
import com.example.meirlen.orc.model.filter.MultipleSelect;
import com.example.meirlen.orc.model.filter.RangeInt;
import com.example.meirlen.orc.model.filter.StringType;
import com.example.meirlen.orc.model.request.Filter;
import com.example.meirlen.orc.presenter.ProductPresenter;
import com.example.meirlen.orc.view.ProductView;
import com.example.meirlen.orc.view.activity.ChildCategoryActivity;
import com.example.meirlen.orc.view.activity.FilterActivity;
import com.example.meirlen.orc.view.adapter.ProductAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ProductFragment extends Fragment implements ProductView {
    BottomSheetDialog dialog;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    ProductPresenter presenter;


    private ProductAdapter adapter;
    List<Product> list = new ArrayList<>();


    public static ProductFragment newInstance() {
        return new ProductFragment();
    }

    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, rootView);
        App.getInstance().createProductComponent().inject(this);

        init();
        presenter.setView(this);
        Filter filter = new Filter();


        BooleanType booleanType = new BooleanType();

        MultipleSelect multipleSelect = new MultipleSelect();


        RangeInt rangeInt = new RangeInt();

        StringType stringType = new StringType();

        filter.setMultipleSelect(multipleSelect);
        filter.setRangeInt(rangeInt);
        filter.set_boolean(booleanType);
        filter.setString(stringType);
        Gson gson = new Gson();
        String modelClass = gson.toJson(filter);
        Log.d("jsonFilter", modelClass);
        presenter.getList(filter);
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

        adapter = new ProductAdapter(list, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


    }


    @Override
    public void getList(List<Product> products) {
        list.clear();
        list.addAll(products);
        adapter.notifyDataSetChanged();
    }


    @OnClick({R.id.filter, R.id.sorting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.filter:
                Intent intent = new Intent(getContext(), FilterActivity.class);
                //  intent.putExtra(ChildCategoryActivity.EXTRA_NAME_PARENT_CATEGORY, mList.get(getAdapterPosition()).getCategoryName());
                //  intent.putExtra(ChildCategoryActivity.EXTRA_ID_PARENT_CATEGORY, String.valueOf(mList.get(getAdapterPosition()).getCategoryId()));
                startActivity(intent);
                break;
            case R.id.sorting:
                View modalbottomsheet = getLayoutInflater().inflate(R.layout.sort_layout, null);
                dialog = new BottomSheetDialog(getContext());
                dialog.setContentView(modalbottomsheet);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);
                setListeners(modalbottomsheet);
                dialog.show();

                break;
        }
    }

    public void setListeners(View listeners) {


        TextView tvSortDateInc = listeners.findViewById(R.id.tvSortDateInc);
        TextView tvSortDateDec = listeners.findViewById(R.id.tvSortDateDec);
        TextView tvSortPriceInc = listeners.findViewById(R.id.tvSortPriceInc);
        TextView tvSortPriceDec = listeners.findViewById(R.id.tvSortPriceDec);
        TextView tvSortRateInc = listeners.findViewById(R.id.tvSortRaitingInc);
        TextView tvSortRateDec = listeners.findViewById(R.id.tvSortRaitingDec);
        TextView tvSortLocationNear = listeners.findViewById(R.id.tvSortLocationNear);
        TextView tvSortLocationFar = listeners.findViewById(R.id.tvSortLocationFar);
        TextView tvSortAreaInc = listeners.findViewById(R.id.tvSortAreaInc);
        TextView tvSortAreaDec = listeners.findViewById(R.id.tvSortAreaDec);

        /*if (StateSaver.searchParams.sort_by.equals(
                String.valueOf(SORT_DATE_ASC))) {
            settvColor(sortLinLayout, tvSortDateInc, true);
        } else if (StateSaver.searchParams.sort_by.equals(
                String.valueOf(SORT_DATE_DESC))) {
            settvColor(sortLinLayout, tvSortDateDec, true);*/


        tvSortDateInc.setOnClickListener(v -> sortItems());
        tvSortDateDec.setOnClickListener(v -> sortItems());
        tvSortPriceInc.setOnClickListener(v -> sortItems());
        tvSortPriceDec.setOnClickListener(v -> sortItems());
        tvSortRateInc.setOnClickListener(v -> sortItems());
        tvSortRateDec.setOnClickListener(v -> sortItems());
        tvSortLocationNear.setOnClickListener(v -> sortItems());
        tvSortLocationFar.setOnClickListener(v -> sortItems());
        tvSortAreaInc.setOnClickListener(v -> sortItems());
        tvSortAreaDec.setOnClickListener(v -> sortItems());
    }


    private void sortItems() {


        if (dialog != null)
            dialog.cancel();

        //   ((SearchListFragment) fragments[1]).setParamsAndSendRequest();
        //   tvSortSearch.setText("Сначала " + tv.getText().toString().toLowerCase());
    }
}
