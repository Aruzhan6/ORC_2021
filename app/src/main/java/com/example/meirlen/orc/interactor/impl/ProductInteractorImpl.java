package com.example.meirlen.orc.interactor.impl;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.interactor.ProductInteractor;
import com.example.meirlen.orc.model.Product;
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
    public Observable<APIResponse<List<Product>>> getList(Filter filter) {
        return restApi.getProducts(filter);
    }
}
