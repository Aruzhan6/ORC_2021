package com.example.meirlen.orc.interactor;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.basket.BasketResponse;
import com.example.meirlen.orc.model.history.HistoryResponse;
import com.example.meirlen.orc.model.order.OrderRequest;
import com.example.meirlen.orc.view.activity.SplashActivity;

import io.reactivex.Observable;

public interface OrderInteractor {

    Observable<APIResponse> sendOrder(String token, String lat , String lng, String address);


}
