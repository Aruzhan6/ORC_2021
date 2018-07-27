package com.example.meirlen.orc.interactor;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.ProductResponse;
import com.example.meirlen.orc.model.basket.BasketResponse;
import com.example.meirlen.orc.model.history.History;
import com.example.meirlen.orc.model.history.HistoryResponse;
import com.example.meirlen.orc.model.request.Filter;

import io.reactivex.Observable;

public interface BasketInteractor {

    Observable<APIResponse<BasketResponse>> getList(String token);


    Observable<APIResponse<CardResponse>> addCart(String token, String id, String decrement);



    Observable<APIResponse<HistoryResponse>> getHistory(String token);

}
