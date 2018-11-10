package com.example.meirlen.orc.interactor;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.qr.QrResponse;


import io.reactivex.Observable;

public interface QrInteractor {

    Observable<APIResponse<QrResponse>> getList(String token);



}
