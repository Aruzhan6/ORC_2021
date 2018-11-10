package com.example.meirlen.orc.presenter;


import com.example.meirlen.orc.base.BasePresenter;
import com.example.meirlen.orc.model.Review;
import com.example.meirlen.orc.view.BasketView;

public interface BasketPresenter extends BasePresenter<BasketView> {

    void getList(String token);

    void addCart(String token, String id, String decrement);

    void addReview(String id, String token, Review review);


    void delete(String id, String token);

    void getHistory(int page, String token);

    void deleteCard(String id, String token);

    void clearCards(String token);
}
