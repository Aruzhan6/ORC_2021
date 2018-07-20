package com.example.meirlen.orc.presenter;


import com.example.meirlen.orc.base.BasePresenter;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.signup.SignupRequest;
import com.example.meirlen.orc.view.CategoryView;
import com.example.meirlen.orc.view.SignUpView;

import java.util.List;

public interface SignUpPresenter extends BasePresenter<SignUpView> {

    void signUp(String number,String name,String city_id);
    void signIn(String number);
    void getProfile(String token);
    void confirm(String sms,String number);


}
