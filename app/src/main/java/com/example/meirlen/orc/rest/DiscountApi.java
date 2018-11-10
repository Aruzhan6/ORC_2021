package com.example.meirlen.orc.rest;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.DiscontResponse;
import com.example.meirlen.orc.model.ProductResponse;
import com.example.meirlen.orc.model.discount.Discount;
import com.example.meirlen.orc.model.request.Filter;

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
