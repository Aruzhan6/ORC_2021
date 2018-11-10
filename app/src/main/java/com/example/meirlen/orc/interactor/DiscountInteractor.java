package com.example.meirlen.orc.interactor;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.DiscontResponse;
import com.example.meirlen.orc.model.ProductResponse;
import com.example.meirlen.orc.model.discount.Discount;
import com.example.meirlen.orc.model.request.Filter;

import java.util.List;

import io.reactivex.Observable;

public interface DiscountInteractor {

    Observable<APIResponse<DiscontResponse>> getList();



}
