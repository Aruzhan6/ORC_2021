package com.pixel.meirlen.orc.interactor.impl;

import com.pixel.meirlen.orc.App;
import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.interactor.DiscountInteractor;
import com.pixel.meirlen.orc.interactor.ProductInteractor;
import com.pixel.meirlen.orc.model.CardResponse;
import com.pixel.meirlen.orc.model.DiscontResponse;
import com.pixel.meirlen.orc.model.ProductResponse;
import com.pixel.meirlen.orc.model.discount.Discount;
import com.pixel.meirlen.orc.model.request.CartRequest;
import com.pixel.meirlen.orc.model.request.Filter;
import com.pixel.meirlen.orc.rest.DiscountApi;
import com.pixel.meirlen.orc.rest.ProductApi;

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
