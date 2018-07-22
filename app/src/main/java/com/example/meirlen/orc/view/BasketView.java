package com.example.meirlen.orc.view;

import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.basket.Basket;

import java.util.List;


public interface BasketView extends LoadingView {

    void getList(List<Basket> products);

    void addCartResponse(CardResponse response);

    void showItemLoading();

    void hideItemLoading();

}
