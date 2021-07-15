package com.pixel.meirlen.orc.presenter.impl;

import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.api.NetworkResponse;
import com.pixel.meirlen.orc.interactor.QrInteractor;

import com.pixel.meirlen.orc.model.qr.QrResponse;
import com.pixel.meirlen.orc.presenter.QrPresenter;
import com.pixel.meirlen.orc.view.QrView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class QrPresenterImpl implements QrPresenter {

    public QrInteractor interactor;
    public QrView pView;
    private Disposable getMessagesDisposable;


    public QrPresenterImpl(QrInteractor interactor) {
        this.interactor = interactor;
    }


    @Override
    public void getList(String token) {
        pView.showLoading();
        getMessagesDisposable = interactor.getList(token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse<QrResponse> apiResponse) -> {
                    pView.hideLoading();
                    if (apiResponse.getIsSuccess() && apiResponse.getStatus() == NetworkResponse.CODE_OK) {
                        if (QrPresenterImpl.this.isViewAttached()) {
                            pView.getList(apiResponse.getData().getQrs());
                        }
                    } else {
                        if (QrPresenterImpl.this.isViewAttached()) {
                            pView.loadingFailed(String.valueOf(apiResponse.getMessage()));
                        }
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        pView.hideLoading();
                        pView.loadingFailed(throwable.getMessage());
                    }
                });

    }


    private boolean isViewAttached() {
        return this.pView != null;
    }


    @Override
    public void setView(QrView view) {
        this.pView=view;

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        //  this.pView = null;

    }


}
