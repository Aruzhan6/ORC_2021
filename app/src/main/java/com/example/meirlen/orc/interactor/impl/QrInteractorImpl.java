package com.example.meirlen.orc.interactor.impl;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.interactor.QrInteractor;

import com.example.meirlen.orc.model.qr.QrResponse;
import com.example.meirlen.orc.rest.QRApi;

import javax.inject.Inject;

import io.reactivex.Observable;


public class QrInteractorImpl implements QrInteractor {


    @Inject
    QRApi restApi;


    public QrInteractorImpl() {
        App.getInstance().createQrComponent().inject(this);
    }


    @Override
    public Observable<APIResponse<QrResponse>> getList(String token) {
        return restApi.getProducers(token);
    }


}
