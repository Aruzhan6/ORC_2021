package com.pixel.meirlen.orc.interactor.impl;

import com.pixel.meirlen.orc.App;
import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.interactor.BasketInteractor;
import com.pixel.meirlen.orc.interactor.OrderInteractor;
import com.pixel.meirlen.orc.model.CardResponse;
import com.pixel.meirlen.orc.model.basket.BasketResponse;
import com.pixel.meirlen.orc.model.history.HistoryResponse;
import com.pixel.meirlen.orc.model.order.OrderRequest;
import com.pixel.meirlen.orc.model.request.CartRequest;
import com.pixel.meirlen.orc.rest.BasketApi;
import com.pixel.meirlen.orc.rest.OrderApi;

import javax.inject.Inject;

import io.reactivex.Observable;


public class OrderInteractorImpl implements OrderInteractor {


    @Inject
    OrderApi restApi;


    public OrderInteractorImpl() {
        App.getInstance().createOrderComponent().inject(this);
    }


    @Override
    public Observable<APIResponse> sendOrder(String token, String lat, String lng,String address) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setLat(lat);
        orderRequest.setLng(lng);
        orderRequest.setInformation(address);
        return restApi.sendOrder(token, orderRequest);
    }
}
