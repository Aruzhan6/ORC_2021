package com.pixel.meirlen.orc.rest;



import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.model.order.OrderRequest;
import com.pixel.meirlen.orc.model.signup.ConfirmRequest;
import com.pixel.meirlen.orc.model.signup.ConfirmResponse;
import com.pixel.meirlen.orc.model.signup.SignupRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OrderApi {

    @POST(ApiMethods.SEND_ORDER)
    Observable<APIResponse> sendOrder(@Header("Token") String token,@Body OrderRequest request);


}
