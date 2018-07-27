package com.example.meirlen.orc.interactor.impl;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.interactor.BasketInteractor;
import com.example.meirlen.orc.interactor.OrderInteractor;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.basket.BasketResponse;
import com.example.meirlen.orc.model.history.HistoryResponse;
import com.example.meirlen.orc.model.order.OrderRequest;
import com.example.meirlen.orc.model.request.CartRequest;
import com.example.meirlen.orc.rest.BasketApi;
import com.example.meirlen.orc.rest.OrderApi;

import javax.inject.Inject;

import io.reactivex.Observable;


public class OrderInteractorImpl implements OrderInteractor {


    @Inject
    OrderApi restApi;


    public OrderInteractorImpl() {
        App.getInstance().createOrderComponent().inject(this);
    }


    @Override
    public Observable<APIResponse> sendOrder(String token, String lat, String lng) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setLat(lat);
        orderRequest.setLng(lng);
        return restApi.sendOrder(token, orderRequest);
    }
}
