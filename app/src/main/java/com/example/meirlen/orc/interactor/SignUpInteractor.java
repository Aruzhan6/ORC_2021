package com.example.meirlen.orc.interactor;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.request.Filter;
import com.example.meirlen.orc.model.signup.ConfirmResponse;
import com.example.meirlen.orc.model.signup.SignupRequest;

import java.util.List;

import io.reactivex.Observable;

public interface SignUpInteractor {

    Observable<APIResponse> signIn(String number);
    Observable<APIResponse> signUp(String number,String name,String city_id);
    Observable<APIResponse<ConfirmResponse>> confirm(String sms, String number);
    Observable<APIResponse<ConfirmResponse>> get_profile(String token);


}
