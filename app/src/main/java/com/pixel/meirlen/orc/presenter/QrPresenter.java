package com.pixel.meirlen.orc.presenter;


import com.pixel.meirlen.orc.base.BasePresenter;
import com.pixel.meirlen.orc.view.QrView;

public interface QrPresenter extends BasePresenter<QrView> {

    void getList(String token);



}
