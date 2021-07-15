package com.pixel.meirlen.orc.interactor;


import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.model.CardResponse;
import com.pixel.meirlen.orc.model.ProductResponse;
import com.pixel.meirlen.orc.model.Review;
import com.pixel.meirlen.orc.model.basket.BasketResponse;
import com.pixel.meirlen.orc.model.history.History;
import com.pixel.meirlen.orc.model.history.HistoryResponse;
import com.pixel.meirlen.orc.model.request.Filter;

import io.reactivex.Observable;

public interface BasketInteractor {

    Observable<APIResponse<BasketResponse>> getList(String token);


    Observable<APIResponse<CardResponse>> addCart(String token, String id, String decrement);

    Observable<APIResponse> addReview( String id,String token, Review review);



    Observable<APIResponse<HistoryResponse>> getHistory(int page,String token);


    Observable<APIResponse> delete(String id,String token);

    Observable<APIResponse> deleteCard(String id,String token);

    Observable<APIResponse> clearCards(String token);

}
