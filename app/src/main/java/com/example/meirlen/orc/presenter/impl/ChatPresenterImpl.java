package com.example.meirlen.orc.presenter.impl;

import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.api.NetworkResponse;
import com.example.meirlen.orc.interactor.ChatInteractor;
import com.example.meirlen.orc.rest.model.Category;
import com.example.meirlen.orc.presenter.ChatPresenter;
import com.example.meirlen.orc.view.ChatView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;



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

        getMessagesDisposable = chatInteractor.getMessages(token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiResponse -> {
                    chatView.hideLoading();
                    if (apiResponse.getIsSuccess() && apiResponse.getStatus() == NetworkResponse.CODE_OK) {
                        if (ChatPresenterImpl.this.isViewAttached()) {
                            chatView.getChats(apiResponse.getData());
                        }
                    } else {
                        if (ChatPresenterImpl.this.isViewAttached()) {
                            chatView.loadingFailed(String.valueOf(apiResponse.getMessage()));
                        }
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        chatView.hideLoading();
                        chatView.loadingFailed(throwable.getMessage());
                    }
                });


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
