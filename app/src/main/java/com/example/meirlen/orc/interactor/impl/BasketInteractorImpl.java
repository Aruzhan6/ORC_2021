package com.example.meirlen.orc.interactor.impl;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.interactor.BasketInteractor;
import com.example.meirlen.orc.interactor.ProductInteractor;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.ProductResponse;
import com.example.meirlen.orc.model.basket.BasketResponse;
import com.example.meirlen.orc.model.history.HistoryResponse;
import com.example.meirlen.orc.model.request.CartRequest;
import com.example.meirlen.orc.model.request.Filter;
import com.example.meirlen.orc.rest.BasketApi;
import com.example.meirlen.orc.rest.ProductApi;

import javax.inject.Inject;

import io.reactivex.Observable;


public class BasketInteractorImpl implements BasketInteractor {


    @Inject
    BasketApi restApi;


    public BasketInteractorImpl() {
        App.getInstance().createBasketComponent().inject(this);
    }


    @Override
    public Observable<APIResponse<BasketResponse>> getList(String token) {
        return restApi.getBaset(token);
    }

    @Override
    public Observable<APIResponse<CardResponse>> addCart(String token, String id, String decrement) {
        CartRequest cartRequest = new CartRequest();
        cartRequest.setProductId(Integer.valueOf(id));
        cartRequest.setDecrement(Integer.valueOf(decrement));
        return restApi.addCart(token, cartRequest);
    }

    @Override
    public Observable<APIResponse<HistoryResponse>> getHistory(String token) {
        return restApi.getHistory(token);
    }
}
