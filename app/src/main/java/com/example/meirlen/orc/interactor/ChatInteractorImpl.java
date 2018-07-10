package com.example.meirlen.orc.interactor;

import android.content.Context;

import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.ResponseGetChat;

import io.reactivex.Observable;


public class ChatInteractorImpl implements ChatInteractor {

    Context context;

    public ChatInteractorImpl(Context context) {
        this.context = context;
    }


    @Override
    public Observable<APIResponse<ResponseGetChat>> getMessages(String authToken) {
        return null;
    }
}
