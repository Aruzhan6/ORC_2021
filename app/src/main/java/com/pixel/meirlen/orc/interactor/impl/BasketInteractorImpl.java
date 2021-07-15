package com.pixel.meirlen.orc.interactor.impl;

import com.pixel.meirlen.orc.App;
import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.interactor.BasketInteractor;
import com.pixel.meirlen.orc.interactor.ProductInteractor;
import com.pixel.meirlen.orc.model.CardResponse;
import com.pixel.meirlen.orc.model.ProductResponse;
import com.pixel.meirlen.orc.model.Review;
import com.pixel.meirlen.orc.model.basket.BasketResponse;
import com.pixel.meirlen.orc.model.history.HistoryResponse;
import com.pixel.meirlen.orc.model.request.CartRequest;
import com.pixel.meirlen.orc.model.request.Filter;
import com.pixel.meirlen.orc.rest.BasketApi;
import com.pixel.meirlen.orc.rest.ProductApi;

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
    public Observable<APIResponse> addReview(String id, String token, Review review) {
        return restApi.addReview(id,token,review);
    }


    @Override
    public Observable<APIResponse<HistoryResponse>> getHistory(int page, String token) {
        return restApi.getHistory(page,token);
    }

    @Override
    public Observable<APIResponse> delete(String id, String token) {
        return restApi.delete(id,token);
    }

    @Override
    public Observable<APIResponse> deleteCard(String id, String token) {
        return restApi.deleteCard(id,token);
    }

    @Override
    public Observable<APIResponse> clearCards(String token) {
        return restApi.clearCards(token);
    }


}
