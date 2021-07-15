package com.pixel.meirlen.orc.interactor;


import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.model.CardResponse;
import com.pixel.meirlen.orc.model.Category;
import com.pixel.meirlen.orc.model.DiscontResponse;
import com.pixel.meirlen.orc.model.ProductResponse;
import com.pixel.meirlen.orc.model.discount.Discount;
import com.pixel.meirlen.orc.model.request.Filter;

import java.util.List;

import io.reactivex.Observable;

public interface DiscountInteractor {

    Observable<APIResponse<DiscontResponse>> getList();



}
