package com.pixel.meirlen.orc.interactor.impl;

import com.pixel.meirlen.orc.App;
import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.interactor.QrInteractor;

import com.pixel.meirlen.orc.model.qr.QrResponse;
import com.pixel.meirlen.orc.rest.QRApi;

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
