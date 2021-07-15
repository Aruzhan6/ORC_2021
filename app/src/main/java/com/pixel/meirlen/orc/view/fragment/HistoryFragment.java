package com.pixel.meirlen.orc.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.pixel.meirlen.orc.App;
import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.helper.AlertUtil;
import com.pixel.meirlen.orc.helper.SessionManager;
import com.pixel.meirlen.orc.interfaces.ItemCallback;
import com.pixel.meirlen.orc.model.CardResponse;
import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.model.Review;
import com.pixel.meirlen.orc.model.history.History;
import com.pixel.meirlen.orc.model.history.HistoryResponse;
import com.pixel.meirlen.orc.presenter.BasketPresenter;
import com.pixel.meirlen.orc.view.BasketView;
import com.pixel.meirlen.orc.view.adapter.HistoryAdapter;
import com.joanfuentes.hintcase.HintCase;
import com.joanfuentes.hintcaseassets.hintcontentholders.SimpleHintContentHolder;
import com.joanfuentes.hintcaseassets.shapeanimators.RevealCircleShapeAnimator;
import com.joanfuentes.hintcaseassets.shapeanimators.UnrevealCircleShapeAnimator;
import com.joanfuentes.hintcaseassets.shapes.CircularShape;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HistoryFragment extends Fragment implements BasketView, ItemCallback {
    BottomSheetDialog dialog;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @BindView(R.id.hintLayout)
    LinearLayout hintLayout;

    @Inject
    BasketPresenter presenter;

    @Inject
    SessionManager sessionManager;
    @BindView(R.id.infoLayout)
    LinearLayout infoLayout;

    private int currentPage = 1;
    private int lastPage;


    private HistoryAdapter adapter;
    List<History> list = new ArrayList<>();
    private int posiition;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;


    private View rootView;
    private static final String TAG = "BasketFragment";


    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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
        rootView = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, rootView);
        App.getInstance().createHistoryComponent().inject(this);

        Log.d(TAG, "onCreateView: " + sessionManager.getAccessToken());

        init();
        presenter.setView(this);
        presenter.getHistory(currentPage, sessionManager.getAccessToken());
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                presenter.getHistory(currentPage, sessionManager.getAccessToken());

            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                        if (currentPage < lastPage) {
                            Log.d(TAG, "onScrolled: current:" + currentPage + " last: " + lastPage);
                            loadMore();
                        }

                    }
                }


            }
        });

        return rootView;
    }

    void loadMore() {
        presenter.getHistory(currentPage + 1, sessionManager.getAccessToken());
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void loadingFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "loadingFailed: " + message);
    }

    @Override
    public void onError(Throwable throwable) {
        Log.d(TAG, "onError: " + throwable.getMessage());
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Log.d(TAG, "showMessage: " + message);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void init() {
        adapter = new HistoryAdapter(list, getActivity(), this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void getList(List<Product> products, int total) {
        Log.d(TAG, "getList: " + products.size());

    }

    @Override
    public void getHistory(HistoryResponse response) {
        lastPage = response.getLastPage();
        currentPage = response.getCurrentPage();
        if (response.getData().size() > 0) {
            infoLayout.setVisibility(View.VISIBLE);
            if (sessionManager.getShowSwipe()) {
                initHintSwipe(rootView);
            }
            hintLayout.setVisibility(View.GONE);
            recyclerView.scrollToPosition(0);
            if (response.getCurrentPage() == 1) {
                list.clear();
            }
            list.addAll(response.getData());
            adapter.notifyDataSetChanged();
        } else {
            infoLayout.setVisibility(View.GONE);
            list.clear();
            adapter.notifyDataSetChanged();
            hintLayout.setVisibility(View.VISIBLE);

        }
    }


    @Override
    public void addCartResponse(CardResponse response) {

    }

    @Override
    public void clearCardsResponse() {

    }

    @Override
    public void deleteResponse() {
        currentPage = 1;
        presenter.getHistory(currentPage, sessionManager.getAccessToken());
    }

    @Override
    public void deleteCardResponse() {

    }


    @Override
    public void showItemLoading() {

    }

    @Override
    public void hideItemLoading() {

    }

    private void initHintSwipe(View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                View actionSearchView = view.findViewById(R.id.infoLayout);
                sessionManager.setShowSwipe(false);
                if (actionSearchView != null) {
                    SimpleHintContentHolder blockInfo =
                            new SimpleHintContentHolder.Builder(actionSearchView.getContext())
                                    .setContentTitle("Свайп")
                                    .setContentText("Подтяyть на лево - удаление,на право - статус")
                                    .setTitleStyle(R.style.title)
                                    .setImageDrawableId(R.drawable.swipe_right)
                                    .setContentStyle(R.style.content)
                                    .setMarginByResourcesId(R.dimen.activity_vertical_margin,
                                            R.dimen.activity_horizontal_margin,
                                            R.dimen.activity_vertical_margin,
                                            R.dimen.activity_horizontal_margin)
                                    .build();
                    new HintCase(actionSearchView.getRootView())
                            .setTarget(actionSearchView, new CircularShape())
                            .setShapeAnimators(new RevealCircleShapeAnimator(),
                                    new UnrevealCircleShapeAnimator())
                            .setHintBlock(blockInfo)
                            .show();
                }
            }
        }, 500);

    }

    @Override
    public void onItemClick(History history) {
        AlertUtil.confirmationAlert(getActivity(), getString(R.string.cancle_request), getString(R.string.question_request), (dialogInterface, i) -> {

        });
    }

    @Override
    public void onAddReview(String review, String id, Review obg) {
        presenter.addReview(id,review,obg);

    }


}
