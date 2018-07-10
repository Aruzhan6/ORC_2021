package com.example.meirlen.orc.presenter.impl;

import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.api.NetworkResponse;
import com.example.meirlen.orc.interactor.ChatInteractor;
import com.example.meirlen.orc.model.ResponseGetChat;
import com.example.meirlen.orc.presenter.ChatPresenter;
import com.example.meirlen.orc.view.ChatView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Bagdat on 1/3/2018.
 */

public class ChatPresenterImpl implements ChatPresenter {

    public ChatInteractor chatInteractor;
    public ChatView chatView;
    private Disposable getMessagesDisposable;

    public ChatPresenterImpl(ChatInteractor chatInteractor) {
        this.chatInteractor = chatInteractor;
    }


    @Override
    public void getMessages(String token) {
        chatView.showLoading();

       /* getMessagesDisposable = chatInteractor.getMessages(token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse<ResponseGetChat> apiResponse) -> {

                    if (apiResponse.error == NetworkResponse.NO_ERROR
                            && apiResponse.code == NetworkResponse.CODE_OK
                            && apiResponse.object != null
                            && apiResponse.object.code == NetworkResponse.OBJECT_CODE_OK) {
                        if (isViewAttached()) {
                            chatView.getChats(apiResponse.object.getChats());


                        }
                    } else {
                        if (isViewAttached()) {
                            if(apiResponse.object.message != null)
                                chatView.loadingFailed(apiResponse.object.message.getRu());

                        }
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                    }
                });

        chatView.hideLoading();*/
    }


    @Override
    public void setView(ChatView view) {
        this.chatView = view;

    }


    private boolean isViewAttached() {
        return this.chatView != null;
    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.chatView = null;

    }


}
