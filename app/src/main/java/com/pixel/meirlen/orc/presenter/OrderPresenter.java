package com.pixel.meirlen.orc.presenter;


import com.pixel.meirlen.orc.base.BasePresenter;
import com.pixel.meirlen.orc.view.FieldView;
import com.pixel.meirlen.orc.view.OrderView;

public interface OrderPresenter extends BasePresenter<OrderView> {

    void sendOrder(String token, String lat , String lng,String address);


}
