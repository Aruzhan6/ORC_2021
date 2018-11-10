package com.example.meirlen.orc.rest;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.DetailResponse;
import com.example.meirlen.orc.model.Review;
import com.example.meirlen.orc.model.basket.BasketResponse;
import com.example.meirlen.orc.model.history.History;
import com.example.meirlen.orc.model.history.HistoryResponse;
import com.example.meirlen.orc.model.request.CartRequest;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BasketApi {

    @GET(ApiMethods.BASKET_GET)
    Observable<APIResponse<BasketResponse>> getBaset(@Header("Token") String token);


    @Headers("Content-Type: application/json")
    @POST(ApiMethods.CART_ADD)
    Observable<APIResponse<CardResponse>> addCart(@Header("Token") String token, @Body CartRequest cartRequest);


    @Headers("Content-Type: application/json")
    @POST(ApiMethods.ADD_REVIEW)
    Observable<APIResponse> addReview(@Path("id") String id, @Header("Token") String token, @Body Review review);

    @Headers("Content-Type: application/json")
    @GET(ApiMethods.HISTORY_GET)
    Observable<APIResponse<HistoryResponse>> getHistory(@Query("page") int page, @Header("Token") String token);


    @Headers("Content-Type: application/json")
    @DELETE(ApiMethods.DELETE_REQUEST)
    Observable<APIResponse> delete(@Path("id") String id, @Header("Token") String token);

    @Headers("Content-Type: application/json")
    @GET(ApiMethods.CART_DELETE)
    Observable<APIResponse> deleteCard(@Path("id") String id, @Header("Token") String token);

    @Headers("Content-Type: application/json")
    @GET(ApiMethods.CART_CLEAR)
    Observable<APIResponse> clearCards(@Header("Token") String token);
}
