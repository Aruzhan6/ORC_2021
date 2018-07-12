package com.example.meirlen.orc.interactor;

import android.content.Context;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.rest.model.Category;
import com.example.meirlen.orc.rest.api.ChatApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


public class ChatInteractorImpl implements ChatInteractor {


    @Inject
    ChatApi chatApi;


    public ChatInteractorImpl() {

        App.getInstance().createChatComponent().inject(this);

    }


    @Override
    public Observable<APIResponse<List<Category>>> getMessages(String authToken) {
        return chatApi.getChat(authToken);
    }


}
