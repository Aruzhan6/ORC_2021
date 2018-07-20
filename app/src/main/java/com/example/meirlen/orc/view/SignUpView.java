package com.example.meirlen.orc.view;

import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.signup.ConfirmResponse;

import java.util.List;


public interface SignUpView extends LoadingView {

    void response(boolean state);
    void response(boolean state, ConfirmResponse response);

}
