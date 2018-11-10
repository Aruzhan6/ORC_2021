package com.example.meirlen.orc.interactor.impl;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.interactor.DiscountInteractor;
import com.example.meirlen.orc.interactor.ProductInteractor;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.DiscontResponse;
import com.example.meirlen.orc.model.ProductResponse;
import com.example.meirlen.orc.model.discount.Discount;
import com.example.meirlen.orc.model.request.CartRequest;
import com.example.meirlen.orc.model.request.Filter;
import com.example.meirlen.orc.rest.DiscountApi;
import com.example.meirlen.orc.rest.ProductApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


public class DiscountInteractorImpl implements DiscountInteractor {


    @Inject
    DiscountApi restApi;


    public DiscountInteractorImpl() {
        App.getInstance().createDiscountComponent().inject(this);
    }


    @Override
    public Observable<APIResponse<DiscontResponse>> getList() {

        return restApi.getDisconts();

    }
}
