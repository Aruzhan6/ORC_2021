package com.example.meirlen.orc.rest;



import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.basket.BasketResponse;
import com.example.meirlen.orc.model.history.History;
import com.example.meirlen.orc.model.history.HistoryResponse;
import com.example.meirlen.orc.model.request.CartRequest;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BasketApi {

    @GET (ApiMethods.BASKET_GET)
    Observable<APIResponse<BasketResponse>> getBaset(@Header("Token") String token);


    @Headers("Content-Type: application/json")
    @POST(ApiMethods.CART_ADD)
    Observable<APIResponse<CardResponse>> addCart(@Header("Token") String token, @Body CartRequest cartRequest);



    @Headers("Content-Type: application/json")
    @GET(ApiMethods.HISTORY_GET)
    Observable<APIResponse<HistoryResponse>> getHistory(@Header("Token") String token);


}
