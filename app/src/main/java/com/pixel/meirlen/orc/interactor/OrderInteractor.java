package com.pixel.meirlen.orc.interactor;


import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.model.CardResponse;
import com.pixel.meirlen.orc.model.basket.BasketResponse;
import com.pixel.meirlen.orc.model.history.HistoryResponse;
import com.pixel.meirlen.orc.model.order.OrderRequest;
import com.pixel.meirlen.orc.view.activity.SplashActivity;

import io.reactivex.Observable;

public interface OrderInteractor {

    Observable<APIResponse> sendOrder(String token, String lat , String lng, String address);


}
