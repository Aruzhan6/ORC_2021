package com.example.meirlen.orc.interactor.impl;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.interactor.ProductInteractor;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.ProductResponse;
import com.example.meirlen.orc.model.request.CartRequest;
import com.example.meirlen.orc.model.request.Filter;
import com.example.meirlen.orc.rest.ProductApi;

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
        return restApi.getProducts(token,filter);
    }

    @Override
    public Observable<APIResponse<CardResponse>> addCart(String token, String id, String decrement){
        CartRequest cartRequest=new CartRequest();
        cartRequest.setProductId(Integer.valueOf(id));
        cartRequest.setDecrement(Integer.valueOf(decrement));
        return restApi.addCart(token,cartRequest);
    }


}
