package com.pixel.meirlen.orc.view;

import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.model.signup.ConfirmResponse;

import java.util.List;


public interface SignUpView extends LoadingView {

    void response(boolean state);
    void response(boolean state, ConfirmResponse response);

}
