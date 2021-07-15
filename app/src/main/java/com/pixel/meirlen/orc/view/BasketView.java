package com.pixel.meirlen.orc.view;

import com.pixel.meirlen.orc.model.CardResponse;
import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.model.history.HistoryResponse;

import java.util.List;


public interface BasketView extends LoadingView {

    void getList(List<Product> products, int total);

    void getHistory(HistoryResponse histories);

    void addCartResponse(CardResponse response);

    void clearCardsResponse();

    void deleteResponse();

    void deleteCardResponse();

    void showItemLoading();

    void hideItemLoading();

}
