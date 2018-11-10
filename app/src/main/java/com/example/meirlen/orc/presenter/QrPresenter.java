package com.example.meirlen.orc.presenter;


import com.example.meirlen.orc.base.BasePresenter;
import com.example.meirlen.orc.view.QrView;

public interface QrPresenter extends BasePresenter<QrView> {

    void getList(String token);



}
