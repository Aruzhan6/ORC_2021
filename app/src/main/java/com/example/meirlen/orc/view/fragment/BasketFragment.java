package com.example.meirlen.orc.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.basket.BasketManager;
import com.example.meirlen.orc.helper.SessionManager;
import com.example.meirlen.orc.interfaces.OnAddCardListener;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.history.HistoryResponse;
import com.example.meirlen.orc.presenter.BasketPresenter;
import com.example.meirlen.orc.view.BasketView;
import com.example.meirlen.orc.view.activity.OrderActivity;
import com.example.meirlen.orc.view.adapter.BasketAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.meirlen.orc.helper.Constans.MINUS;


public class BasketFragment extends Fragment implements BasketView, OnAddCardListener {
    BottomSheetDialog dialog;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    BasketPresenter presenter;

    @Inject
    SessionManager sessionManager;
    @BindView(R.id.totalSum)
    TextView totalSum;
    @BindView(R.id.design_bottom)
    LinearLayout designBottom;
    @BindView(R.id.topLayout)
    LinearLayout topLayout;
    @BindView(R.id.txtInfo)
    TextView txtInfo;

    private int total;
    private String decrement;


    private BasketAdapter adapter;
    List<Product> list = new ArrayList<>();
    private int posiition;


    private static final String TAG = "BasketFragment";

    @Inject
    BasketManager basketManager;


    public static BasketFragment newInstance() {
        return new BasketFragment();
    }

    public static BasketFragment newInstance(String param1, String param2) {
        BasketFragment fragment = new BasketFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_basket, container, false);
        ButterKnife.bind(this, rootView);
        designBottom.setVisibility(View.GONE);
        topLayout.setVisibility(View.GONE);
        App.getInstance().createBasketComponent().inject(this);

        Log.d(TAG, "onCreateView: " + sessionManager.getAccessToken());

        init();
        presenter.setView(this);
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
        adapter = new BasketAdapter(this, list, getActivity());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getList(List<Product> products, int total) {
        this.total = total;
        Log.d(TAG, "getList: " + products.size());
        list.clear();
        list.addAll(products);
        adapter.notifyDataSetChanged();
        showTotal();

    }

    private void showTotal() {
        if (list.size() > 0) {
            designBottom.setVisibility(View.VISIBLE);
            topLayout.setVisibility(View.VISIBLE);
            txtInfo.setText("");
        } else {
            designBottom.setVisibility(View.GONE);
            topLayout.setVisibility(View.GONE);
            txtInfo.setText(R.string.empty_basket);
        }
        totalSum.setText(String.valueOf(total) + getString(R.string.tenge));
    }

    @Override
    public void getHistory(HistoryResponse histories) {

    }


    @Override
    public void onAddCard(String id, String decrement, int position) {
        this.decrement = decrement;
        this.posiition = position;
        presenter.addCart(sessionManager.getAccessToken(), id, decrement);

    }

    @Override
    public void onDeleteCard(int position, double price) {
        this.posiition = position;
        total = (int) (total - price);
        presenter.deleteCard(list.get(position).getCartId(), sessionManager.getAccessToken());
    }


    @Override
    public void addCartResponse(CardResponse response) {
        calculateCount();
        Product Product = list.get(posiition);
        basketManager.update(response, Product);
        if (response.getCartCount() == null || response.getCartCount() == 0) {
            list.remove(posiition);
            adapter.notifyDataSetChanged();
        } else {
            Product.setCartCount(String.valueOf(response.getCartCount()));
            Product.setTotal_price(response.getTotalPrice());
            Product.setCartId(response.getCartId());
            Product.setCartProductId(response.getCartProductId());
            list.set(posiition, Product);
        }
        adapter.notifyDataSetChanged();
        showTotal();
    }

    @Override
    public void clearCardsResponse() {
        list.clear();
        adapter.notifyDataSetChanged();
        basketManager.update(0);
        showTotal();
    }

    private void calculateCount() {
        if (decrement.equals(MINUS)) {
            if (list.get(posiition).getDiscount() != null) {
                int price = list.get(posiition).getProductPrice();
                int res = price - (price * list.get(posiition).getDiscount().getDiscountPercent() / 100);
                total = total - res;
            } else {
                total = total - list.get(posiition).getProductPrice();
            }
        } else {
            if (list.get(posiition).getDiscount() != null) {
                int price = list.get(posiition).getProductPrice();
                int res = price - (price * list.get(posiition).getDiscount().getDiscountPercent() / 100);
                total = total + res;
            } else {
                total = total + list.get(posiition).getProductPrice();
            }
        }
    }

    @Override
    public void deleteResponse() {

    }

    @Override
    public void deleteCardResponse() {
        basketManager.deleteItem(Integer.valueOf(list.get(posiition).getCartCount()));
        list.remove(posiition);
        adapter.notifyDataSetChanged();
        showTotal();
    }


    @Override
    public void showItemLoading() {
        adapter.showLoading();
    }

    @Override
    public void hideItemLoading() {
        adapter.hideLoading();

    }


    @OnClick(R.id.sendButton)
    public void onViewClicked() {

        Intent intent = new Intent(getContext(), OrderActivity.class);
        intent.putExtra(OrderFragment.EXTRA_TOTAL, totalSum.getText().toString());
        intent.putExtra(OrderFragment.EXTRA_TOTAL_SIZE, String.valueOf(list.size()));
        startActivity(intent);
    }

    @OnClick(R.id.clearBasket)
    public void exitApp() {
        String message = "Очистить корзину";
        String button1String = "Ok";
        String button2String = "Отменить";

        AlertDialog.Builder ad = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        ad.setMessage(message); // сообщение
        ad.setPositiveButton(button1String, (dialog, arg1) -> {

            presenter.clearCards(sessionManager.getAccessToken());


        });
        ad.setNegativeButton(button2String, (dialog, arg1) -> {

        });

        ad.show();
    }


}
