package com.pixel.meirlen.orc.interactor;


import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.model.request.Filter;
import com.pixel.meirlen.orc.model.signup.ConfirmResponse;
import com.pixel.meirlen.orc.model.signup.SignupRequest;

import java.util.List;

import io.reactivex.Observable;

public interface SignUpInteractor {

    Observable<APIResponse> signIn(String number,String apiKey);
    Observable<APIResponse> signUp(String number,String name,String city_id);
    Observable<APIResponse<ConfirmResponse>> confirm(String sms, String number);
    Observable<APIResponse<ConfirmResponse>> get_profile(String token);


}
