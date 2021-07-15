package com.pixel.meirlen.orc.interactor;


import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.model.qr.QrResponse;


import io.reactivex.Observable;

public interface QrInteractor {

    Observable<APIResponse<QrResponse>> getList(String token);



}
