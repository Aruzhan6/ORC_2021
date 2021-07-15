package com.pixel.meirlen.orc.interactor.impl;

import com.pixel.meirlen.orc.App;
import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.interactor.ProductInteractor;
import com.pixel.meirlen.orc.model.CardResponse;
import com.pixel.meirlen.orc.model.DetailResponse;
import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.model.ProductResponse;
import com.pixel.meirlen.orc.model.request.CartRequest;
import com.pixel.meirlen.orc.model.request.Filter;
import com.pixel.meirlen.orc.rest.ProductApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


public class ProductInteractorImpl implements ProductInteractor {


    @Inject
    ProductApi restApi;


    public ProductInteractorImpl() {
        App.getInstance().createProductComponent().inject(this);
    }


    @Override
    public Observable<APIResponse<ProductResponse>> getList(String token, Filter filter) {
        return restApi.getProducts(token, filter);
    }

    @Override
    public Observable<APIResponse<ProductResponse>> getCategoryId(String id, String token, Filter filter) {
        return restApi.getProductsById(id,token, filter);
    }

    @Override
    public Observable<APIResponse<DetailResponse>> getProductById(String id, String token) {
        return restApi.getById(id,token);
    }

    @Override
    public Observable<APIResponse<ProductResponse>> getProducerById(String id, String token, Filter filter) {
        return restApi.getProducerProducts(id,token, filter);
    }

    @Override
    public Observable<APIResponse<ProductResponse>> getFavourities(String token) {
        return restApi.getFavourities(token);
    }

    @Override
    public Observable<APIResponse<CardResponse>> addCart(String token, String id, String decrement) {
        CartRequest cartRequest = new CartRequest();
        cartRequest.setProductId(Integer.valueOf(id));
        cartRequest.setDecrement(Integer.valueOf(decrement));
        return restApi.addCart(token, cartRequest);
    }

    @Override
    public Observable<APIResponse<Product>> markFavourite(String token, Integer product_id) {
        return restApi.markFavourite(token, product_id);
    }


}
