package com.example.meirlen.orc.presenter;


import com.example.meirlen.orc.base.BasePresenter;
import com.example.meirlen.orc.model.basket.Basket;
import com.example.meirlen.orc.model.request.Filter;
import com.example.meirlen.orc.view.BasketView;
import com.example.meirlen.orc.view.ProductView;

public interface BasketPresenter extends BasePresenter<BasketView> {

    void getList(String token);

    void addCart(String token,String id,String decrement);

    void getHistory(String token);


}
