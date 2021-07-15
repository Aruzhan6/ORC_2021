package com.pixel.meirlen.orc.presenter;


import com.pixel.meirlen.orc.base.BasePresenter;
import com.pixel.meirlen.orc.model.Category;
import com.pixel.meirlen.orc.model.signup.SignupRequest;
import com.pixel.meirlen.orc.view.CategoryView;
import com.pixel.meirlen.orc.view.SignUpView;

import java.util.List;

public interface SignUpPresenter extends BasePresenter<SignUpView> {

    void signUp(String number,String name,String city_id);
    void signIn(String number,String apiKey);
    void getProfile(String token);
    void confirm(String sms,String number);


}
