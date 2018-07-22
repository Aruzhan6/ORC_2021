package com.example.meirlen.orc.presenter.impl;

import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.api.NetworkResponse;
import com.example.meirlen.orc.interactor.BasketInteractor;
import com.example.meirlen.orc.interactor.ProductInteractor;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.ProductResponse;
import com.example.meirlen.orc.model.basket.Basket;
import com.example.meirlen.orc.model.basket.BasketResponse;
import com.example.meirlen.orc.model.request.Filter;
import com.example.meirlen.orc.presenter.BasketPresenter;
import com.example.meirlen.orc.presenter.ProductPresenter;
import com.example.meirlen.orc.view.BasketView;
import com.example.meirlen.orc.view.ProductView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class BasketPresenterImpl implements BasketPresenter {

    public BasketInteractor interactor;
    public BasketView pView;
    private Disposable getMessagesDisposable;


    public BasketPresenterImpl(BasketInteractor interactor) {
        this.interactor = interactor;
    }


    @Override
    public void getList(String token) {

        pView.showLoading();
        getMessagesDisposable = interactor.getList(token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse<BasketResponse> apiResponse) -> {
                    pView.hideLoading();
                    if (apiResponse.getIsSuccess() && apiResponse.getStatus() == NetworkResponse.CODE_OK) {
                        if (BasketPresenterImpl.this.isViewAttached()) {
                            pView.getList(apiResponse.getData().getList());
                        }
                    } else {
                        if (BasketPresenterImpl.this.isViewAttached()) {
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

    @Override
    public void addCart(String token, String id, String decrement) {
        pView.showItemLoading();
        getMessagesDisposable = interactor.addCart(token, id, decrement)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse<CardResponse> apiResponse) -> {
                    pView.hideItemLoading();
                    if (apiResponse.getIsSuccess() && apiResponse.getStatus() == NetworkResponse.CODE_OK) {
                        if (BasketPresenterImpl.this.isViewAttached()) {
                            pView.addCartResponse(apiResponse.getData());
                        }
                    } else {
                        if (BasketPresenterImpl.this.isViewAttached()) {
                            pView.loadingFailed(String.valueOf(apiResponse.getMessage()));
                        }
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        pView.hideItemLoading();
                        pView.loadingFailed(throwable.getMessage());
                    }
                });


    }
    private boolean isViewAttached() {
        return this.pView != null;
    }


    @Override
    public void setView(BasketView view) {
        this.pView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.pView = null;

    }


}
