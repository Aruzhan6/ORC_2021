package com.example.meirlen.orc.interactor.impl;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.interactor.ProductInteractor;
import com.example.meirlen.orc.interactor.SignUpInteractor;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.request.Filter;
import com.example.meirlen.orc.model.signup.ConfirmRequest;
import com.example.meirlen.orc.model.signup.ConfirmResponse;
import com.example.meirlen.orc.model.signup.SignupRequest;
import com.example.meirlen.orc.rest.ProductApi;
import com.example.meirlen.orc.rest.SignApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


public class SignUpInteractorImpl implements SignUpInteractor {


    @Inject
    SignApi restApi;


    public SignUpInteractorImpl() {
        App.getInstance().createSignUpComponent().inject(this);
    }


    @Override
    public Observable<APIResponse> signIn(String number, String apiKey) {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setPhone(number);
        signupRequest.setKey(apiKey);

        return restApi.signin(signupRequest);
    }

    @Override
    public Observable<APIResponse> signUp(String number, String name, String city_id) {

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setCityId(city_id);
        signupRequest.setName(name);
        signupRequest.setPhone(number);
        return restApi.signup(signupRequest);

    }

    @Override
    public Observable<APIResponse<ConfirmResponse>> confirm(String sms, String number) {
        ConfirmRequest confirmRequest = new ConfirmRequest();
        confirmRequest.setSms(sms);
        confirmRequest.setPhone(number);
        return restApi.confirm(confirmRequest);
    }

    @Override
    public Observable<APIResponse<ConfirmResponse>> get_profile(String token) {
        return restApi.get_profile(token);
    }


}
