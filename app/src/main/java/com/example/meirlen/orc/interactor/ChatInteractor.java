package com.example.meirlen.orc.interactor;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.ResponseGetChat;

import io.reactivex.Observable;

public interface ChatInteractor {

    Observable<APIResponse<ResponseGetChat>> getMessages(String authToken);

}
