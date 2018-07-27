package com.example.meirlen.orc.view;

import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.basket.Basket;
import com.example.meirlen.orc.model.history.History;

import java.util.List;


public interface BasketView extends LoadingView {

    void getList(List<Basket> products);

    void getHistory(List<History> histories);

    void addCartResponse(CardResponse response);

    void showItemLoading();

    void hideItemLoading();

}
