package com.example.meirlen.orc.rest;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.ProductResponse;
import com.example.meirlen.orc.model.request.CartRequest;
import com.example.meirlen.orc.model.request.Filter;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ProductApi {


    @Headers("Content-Type: application/json")
    @POST(ApiMethods.PRODUCT_GET)
    Observable<APIResponse<ProductResponse>> getProducts(@Header("Token") String token, @Body Filter filter);

    @Headers("Content-Type: application/json")
    @GET(ApiMethods.PRODUCT_GET_FAV)
    Observable<APIResponse<ProductResponse>> getFavourities(@Header("Token") String token);

    @Headers("Content-Type: application/json")
    @POST(ApiMethods.CART_ADD)
    Observable<APIResponse<CardResponse>> addCart(@Header("Token") String token, @Body CartRequest cartRequest);

    @FormUrlEncoded
    @POST(ApiMethods.PRODUCT_ADD_FAV)
    Observable<APIResponse<Product>> markFavourite(@Header("Token") String token, @Field("product_id") Integer product_id);

}
