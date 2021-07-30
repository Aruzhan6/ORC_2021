package com.pixel.meirlen.orc.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pixel.meirlen.orc.App;
import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.basket.BasketManager;
import com.pixel.meirlen.orc.helper.FilterFactory;
import com.pixel.meirlen.orc.helper.GlobalState;
import com.pixel.meirlen.orc.helper.SessionManager;
import com.pixel.meirlen.orc.helper.AppUtil;
import com.pixel.meirlen.orc.interactor.ProductInteractor;
import com.pixel.meirlen.orc.interfaces.FavouriteMethodCaller;
import com.pixel.meirlen.orc.interfaces.ItemClickListener;
import com.pixel.meirlen.orc.interfaces.OnAddCardListener;
import com.pixel.meirlen.orc.interfaces.OnSearchListener;
import com.pixel.meirlen.orc.interfaces.UpdateProductList;
import com.pixel.meirlen.orc.model.CardResponse;
import com.pixel.meirlen.orc.model.DetailResponse;
import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.model.discount.Discount;
import com.pixel.meirlen.orc.model.request.Filter;
import com.pixel.meirlen.orc.presenter.ProductPresenter;
import com.pixel.meirlen.orc.presenter.impl.DiscPresenterImpl;
import com.pixel.meirlen.orc.presenter.impl.FavPresenterImpl;
import com.pixel.meirlen.orc.presenter.impl.PoductPresenterImpl;
import com.pixel.meirlen.orc.presenter.impl.ProducerPresenterImpl;
import com.pixel.meirlen.orc.presenter.impl.SearchPresenterImpl;
import com.pixel.meirlen.orc.view.ProductView;
import com.pixel.meirlen.orc.view.activity.DetailActivity;
import com.pixel.meirlen.orc.view.activity.FilterActivity;
import com.pixel.meirlen.orc.view.adapter.ProductAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pixel.meirlen.orc.helper.Constans.RQ_FILTER;
import static com.pixel.meirlen.orc.helper.Constans.RQ_UPDATE;
import static com.pixel.meirlen.orc.helper.ProductViewEnum.*;
import static com.pixel.meirlen.orc.view.activity.ProductListActivity.EXTRA_DISCOUNY_PRODUCTS;
import static com.pixel.meirlen.orc.view.activity.ProductListActivity.EXTRA_FILTER;
import static com.pixel.meirlen.orc.view.activity.ProductListActivity.EXTRA_ID_CATEGORY;
import static com.pixel.meirlen.orc.view.activity.ProductListActivity.EXTRA_PRODUCER_ID;
import static com.pixel.meirlen.orc.view.activity.SearchActivity.EXTRA_SEARCH_KEY;


public class ProductFragment extends Fragment implements ProductView, OnAddCardListener, FavouriteMethodCaller, OnSearchListener, ItemClickListener, UpdateProductList {
    public static final String EXTRA_FAVOURITES = "extra:fav";

    public static final String EXTRA_UPDATE_PRODUCT = "extra:upd";


    BottomSheetDialog dialog;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.filterLayout)
    LinearLayout filterLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    ProductPresenter presenter;

    @Inject
    ProductInteractor iterator;

    @Inject
    SessionManager sessionManager;
    @BindView(R.id.emptyTxt)
    TextView emptyTxt;

    private ProductAdapter adapter;
    List<Product> list = new ArrayList<>();
    private int posiition;
    private static final String TAG = "ProductFragment";
    private int viewType = 0;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    Gson gson;

    @Inject
    BasketManager basketManager;

    private int itemPos;
    int SORT_DATE = 0, SORT_PRICE = 1;
    int SORT_TYPE;
    boolean SORT_VALUE;

    private String id;

    public static ProductFragment newInstance() {
        return new ProductFragment();
    }

    public static ProductFragment newInstance(int param1) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_FAVOURITES, param1);
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
        View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, rootView);
        App.getInstance().createProductComponent().inject(this);
        init();
        assert getArguments() != null;
        viewType = getArguments().getInt(EXTRA_FAVOURITES);
        initPresenter();
        mSwipeRefreshLayout.setOnRefreshListener(this::initPresenter);
        sessionManager.setKeyWord("");
        sessionManager.setTo("");
        sessionManager.setFrom("");
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        {
            if (viewType == FAVOURITE) {
                initPresenter();

            }


        }
    }

    private void initPresenter() {
        switch (viewType) {
            case PUBLIC:
                presenter = new PoductPresenterImpl(iterator);
                presenter.setView(this);
                id = Objects.requireNonNull(getActivity()).getIntent().getStringExtra(EXTRA_ID_CATEGORY);
                presenter.getListById(id, sessionManager.getAccessToken(), FilterFactory.createFilter());
                break;
            case PRODUCER:
                presenter = new ProducerPresenterImpl(iterator);
                presenter.setView(this);
                filterLayout.setVisibility(View.GONE);
                id = Objects.requireNonNull(getActivity()).getIntent().getStringExtra(EXTRA_PRODUCER_ID);
                presenter.getProducerById(id, sessionManager.getAccessToken(), FilterFactory.createFilter());
                break;
            case FAVOURITE:
                presenter = new FavPresenterImpl(iterator);
                presenter.setView(this);
                filterLayout.setVisibility(View.GONE);
                presenter.getFavourities(sessionManager.getAccessToken());
                break;
            case SEARCH:
                presenter = new SearchPresenterImpl(iterator);
                presenter.setView(this);
                filterLayout.setVisibility(View.GONE);
                onSearchByName(Objects.requireNonNull(getActivity()).getIntent().getStringExtra(EXTRA_SEARCH_KEY));
                break;
            case DISCOUNT:
                presenter = new DiscPresenterImpl(iterator);
                presenter.setView(this);
                hideLoading();
                Discount ob = gson.fromJson(Objects.requireNonNull(getActivity()).getIntent().getStringExtra(EXTRA_DISCOUNY_PRODUCTS), Discount.class);
                filterLayout.setVisibility(View.GONE);
                getList(ob.getRandom_products());
                break;
            default:
                presenter = new PoductPresenterImpl(iterator);
                presenter.setView(this);
                filterLayout.setVisibility(View.GONE);

        }

    }

    @Override
    public void showLoading() {
        emptyTxt.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void loadingFailed(String message) {
        Log.d(TAG, "loadingFailed: " + message);
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
        adapter = new ProductAdapter(this, this, list, getActivity(), this);
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
        if (products.size() > 0)
            emptyTxt.setVisibility(View.GONE);
        else
            emptyTxt.setVisibility(View.VISIBLE);
    }

    @Override
    public void addCartResponse(CardResponse response) {
        Product product = list.get(posiition);
        basketManager.update(response, product);
        product.setCartCount(String.valueOf(response.getCartCount()));
        product.setCartId(String.valueOf(response.getCartId()));
        product.setCartProductId(response.getCartProductId());
        list.set(posiition, product);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void detailResponse(DetailResponse response) {

    }

    @Override
    public void showItemLoading(int requestCode) {
        adapter.showLoading(requestCode);
    }

    @Override
    public void hideItemLoading(int requestCode) {
        adapter.hideLoading(requestCode);
    }


    @Override
    public void markFavourite(Product response) {
        AppUtil.showGson(response, gson, TAG);
        if (viewType == FAVOURITE) {
            checkFav(response);
        } else {
            GlobalState.product_update = true;

        }
    }

    private void checkFav(Product response) {
        if (!response.getFavourite()) {
            list.remove(response);
            adapter.notifyDataSetChanged();
            showLoading();
            removeItemList(response);
        }

    }


    private void removeItemList(Product item) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProductId().equals(item.getProductId())) {
                list.remove(i);
                break;
            }
        }
        hideLoading();
        adapter.notifyDataSetChanged();
        if (list.size() > 0)
            emptyTxt.setVisibility(View.GONE);
        else
            emptyTxt.setVisibility(View.VISIBLE);
    }


    @OnClick({R.id.filter, R.id.sorting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.filter:
                Intent intent = new Intent(getContext(), FilterActivity.class);
                intent.putExtra(EXTRA_ID_CATEGORY, Objects.requireNonNull(getActivity()).getIntent().getStringExtra(EXTRA_ID_CATEGORY));
                startActivityForResult(intent, RQ_FILTER);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RQ_FILTER) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra(EXTRA_FILTER);
                Filter ob = gson.fromJson(result, Filter.class);
                presenter.getListById(getActivity().getIntent().getStringExtra(EXTRA_ID_CATEGORY), sessionManager.getAccessToken(), ob);
                AppUtil.showGson(ob, gson, TAG);
            }
        }

        if (requestCode == RQ_UPDATE) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra(EXTRA_UPDATE_PRODUCT);
                Product product = gson.fromJson(result, Product.class);
                if (product != null) {
                    if (product.getProducer() == null) {
                        product.setProducer(list.get(itemPos).getProducer());
                    }
                } else {
                    product = list.get(itemPos);
                    product.setCartCount(null);
                }
                list.set(itemPos, product);
                adapter.notifyItemChanged(itemPos);
                AppUtil.showGson(product, gson, TAG);
                if (!product.getFavourite()) {
                    if (viewType == FAVOURITE) {
                        list.remove(itemPos);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }

    public void setListeners(View listeners) {
        TextView tvSortDateInc = listeners.findViewById(R.id.tvSortDateInc);
        TextView tvSortDateDec = listeners.findViewById(R.id.tvSortDateDec);
        TextView tvSortPriceInc = listeners.findViewById(R.id.tvSortPriceInc);
        TextView tvSortPriceDec = listeners.findViewById(R.id.tvSortPriceDec);
//


        if (SORT_DATE == SORT_TYPE && SORT_VALUE ) {
            tvSortDateInc.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rectangle_blue));
            tvSortDateInc.setTextColor(getResources().getColor(R.color.white));
        }

        if (SORT_DATE == SORT_TYPE && !SORT_VALUE) {
            tvSortDateDec.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rectangle_blue));
            tvSortDateDec.setTextColor(getResources().getColor(R.color.white));
        }

        if (SORT_PRICE == SORT_TYPE && SORT_VALUE) {
            tvSortPriceInc.setBackground(ContextCompat.getDrawable(getContext(),  R.drawable.rectangle_blue));
            tvSortPriceInc.setTextColor(getResources().getColor(R.color.white));
        }

        if (SORT_PRICE == SORT_TYPE && !SORT_VALUE) {
            tvSortPriceDec.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rectangle_blue));
            tvSortPriceDec.setTextColor(getResources().getColor(R.color.white));
        }

        tvSortDateInc.setOnClickListener(v -> sortItems(SORT_DATE, true));
        tvSortDateDec.setOnClickListener(v -> sortItems(SORT_DATE, false));
        tvSortPriceInc.setOnClickListener(v -> sortItems(SORT_PRICE, true));
        tvSortPriceDec.setOnClickListener(v -> sortItems(SORT_PRICE, false));
    }

    private void sortItems(int sortType, boolean sortValue) {
        if (SORT_DATE == sortType) {
            Filter filter = FilterFactory.createFilter();
            filter.setDirectionup_newness(sortValue);
            presenter.getListById(getActivity().getIntent().getStringExtra(EXTRA_ID_CATEGORY), sessionManager.getAccessToken(), filter);
            SORT_TYPE = sortType;
            SORT_VALUE = sortValue;
        }
        if (SORT_PRICE == sortType) {
            Filter filter = FilterFactory.createFilter();
            filter.setDirectionup_price(sortValue);
            presenter.getListById(getActivity().getIntent().getStringExtra(EXTRA_ID_CATEGORY), sessionManager.getAccessToken(), filter);
            SORT_TYPE = sortType;
            SORT_VALUE = sortValue;
        }
        if (dialog != null)
            dialog.cancel();
    }

    @Override
    public void onAddCard(String id, String decrement, int position) {
        this.posiition = position;
        presenter.addCart(sessionManager.getAccessToken(), id, decrement);

    }

    @Override
    public void onDeleteCard(int position, double price) {

    }


    @Override
    public void markSpaceFavourie(int space_id) {
        presenter.markFavourite(sessionManager.getAccessToken(), space_id);

    }

    @Override
    public void onSearchByName(String key) {
        presenter.getList(sessionManager.getAccessToken(), FilterFactory.createFilter(key));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.destroy();
    }

    @Override
    public void onItemClick(int pos) {
        this.itemPos = pos;
        startActivityForResult(DetailActivity.detailProductIntent(getActivity(), list.get(pos)), RQ_UPDATE);
    }


    @Override
    public void update() {
        initPresenter();
    }
}
