package com.pixel.meirlen.orc.rest;



import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.model.signup.ConfirmRequest;
import com.pixel.meirlen.orc.model.signup.ConfirmResponse;
import com.pixel.meirlen.orc.model.signup.SignupRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SignApi {

    @POST(ApiMethods.SIGNUP)
    Observable<APIResponse> signup(@Body SignupRequest request);


    @POST(ApiMethods.CONFIRM)
    Observable<APIResponse<ConfirmResponse>> confirm(@Body ConfirmRequest request);

    @POST(ApiMethods.SIGNIN)
    Observable<APIResponse> signin(@Body SignupRequest request);

    @GET(ApiMethods.GET_PROFILE)
    Observable<APIResponse<ConfirmResponse>> get_profile(@Header("Token") String token);

}
