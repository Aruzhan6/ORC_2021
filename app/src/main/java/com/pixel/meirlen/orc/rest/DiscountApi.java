package com.pixel.meirlen.orc.rest;


import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.model.DiscontResponse;
import com.pixel.meirlen.orc.model.ProductResponse;
import com.pixel.meirlen.orc.model.discount.Discount;
import com.pixel.meirlen.orc.model.request.Filter;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface DiscountApi {


    @Headers("Content-Type: application/json")
    @GET(ApiMethods.GET_DISCOUNTS)
    Observable<APIResponse<DiscontResponse>> getDisconts();


}
