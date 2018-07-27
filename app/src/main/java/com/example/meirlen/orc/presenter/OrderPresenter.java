package com.example.meirlen.orc.presenter;


import com.example.meirlen.orc.base.BasePresenter;
import com.example.meirlen.orc.view.FieldView;
import com.example.meirlen.orc.view.OrderView;

public interface OrderPresenter extends BasePresenter<OrderView> {

    void sendOrder(String token, String lat , String lng);


}
