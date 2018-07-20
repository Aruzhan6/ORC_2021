package com.example.meirlen.orc.rest;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.ProductResponse;
import com.example.meirlen.orc.model.request.CartRequest;
import com.example.meirlen.orc.model.request.Filter;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ProductApi {


    @Headers("Content-Type: application/json")
    @POST(ApiMethods.PRODUCT_GET)
    Observable<APIResponse<ProductResponse>> getProducts(@Header("Token") String token, @Body Filter filter);

    @Headers("Content-Type: application/json")
    @POST(ApiMethods.CART_ADD)
    Observable<APIResponse<CardResponse>> addCart(@Header("Token") String token, @Body CartRequest cartRequest);

}
