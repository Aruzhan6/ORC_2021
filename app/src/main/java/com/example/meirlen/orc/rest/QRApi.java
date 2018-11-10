package com.example.meirlen.orc.rest;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.qr.QrResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface QRApi {


    @Headers("Content-Type: application/json")
    @GET(ApiMethods.QR_GET)
    Observable<APIResponse<QrResponse>> getProducers(@Header("Token") String token);


}
