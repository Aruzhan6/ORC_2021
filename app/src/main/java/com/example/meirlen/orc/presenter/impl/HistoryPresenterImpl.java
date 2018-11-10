package com.example.meirlen.orc.presenter.impl;

import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.api.NetworkResponse;
import com.example.meirlen.orc.interactor.BasketInteractor;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.Review;
import com.example.meirlen.orc.model.basket.BasketResponse;
import com.example.meirlen.orc.model.history.HistoryResponse;
import com.example.meirlen.orc.presenter.BasketPresenter;
import com.example.meirlen.orc.view.BasketView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HistoryPresenterImpl implements BasketPresenter {

    public BasketInteractor interactor;
    public BasketView pView;
    private Disposable getMessagesDisposable;


    public HistoryPresenterImpl(BasketInteractor interactor) {
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
                        if (HistoryPresenterImpl.this.isViewAttached()) {
                            pView.getList(apiResponse.getData().getList(),apiResponse.getData().getTotal().intValue());
                        }
                    } else {
                        if (HistoryPresenterImpl.this.isViewAttached()) {
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
                        if (HistoryPresenterImpl.this.isViewAttached()) {
                            pView.addCartResponse(apiResponse.getData());
                        }
                    } else {
                        if (HistoryPresenterImpl.this.isViewAttached()) {
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

    @Override
    public void addReview(String id, String token, Review review) {

    }

    @Override
    public void delete(String id, String token) {

        pView.showItemLoading();
        getMessagesDisposable = interactor.delete( id,token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse apiResponse) -> {
                    pView.hideItemLoading();
                    if (apiResponse.getIsSuccess() && apiResponse.getStatus() == NetworkResponse.CODE_OK) {
                        if (HistoryPresenterImpl.this.isViewAttached()) {
                            pView.deleteResponse();

                        }
                    } else {
                        if (HistoryPresenterImpl.this.isViewAttached()) {
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

    @Override
    public void getHistory(int page,String token) {


        pView.showLoading();
        getMessagesDisposable = interactor.getHistory(page,token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse<HistoryResponse> apiResponse) -> {
                    pView.hideLoading();
                    if (apiResponse.getIsSuccess() && apiResponse.getStatus() == NetworkResponse.CODE_OK) {
                        if (HistoryPresenterImpl.this.isViewAttached()) {
                            pView.getHistory(apiResponse.getData());
                        }
                    } else {
                        if (HistoryPresenterImpl.this.isViewAttached()) {
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
    public void deleteCard(String id, String token) {

    }

    @Override
    public void clearCards(String token) {

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

    }


}
