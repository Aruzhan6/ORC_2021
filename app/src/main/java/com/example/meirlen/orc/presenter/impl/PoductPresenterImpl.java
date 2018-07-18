package com.example.meirlen.orc.presenter.impl;

import com.example.meirlen.orc.api.NetworkResponse;
import com.example.meirlen.orc.interactor.CategoryInteractor;
import com.example.meirlen.orc.interactor.ProductInteractor;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.request.Filter;
import com.example.meirlen.orc.presenter.CategoryPresenter;
import com.example.meirlen.orc.presenter.ProductPresenter;

import com.example.meirlen.orc.view.ProductView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


public class PoductPresenterImpl implements ProductPresenter {

    public ProductInteractor interactor;
    public ProductView pView;
    private Disposable getMessagesDisposable;


    public PoductPresenterImpl(ProductInteractor interactor) {
        this.interactor = interactor;
    }


    @Override
    public void getList(Filter filter) {
        pView.showLoading();
        getMessagesDisposable = interactor.getList(filter)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiResponse -> {
                    pView.hideLoading();
                    if (apiResponse.getIsSuccess() && apiResponse.getStatus() == NetworkResponse.CODE_OK) {
                        if (PoductPresenterImpl.this.isViewAttached()) {
                            pView.getList(apiResponse.getData());
                        }
                    } else {
                        if (PoductPresenterImpl.this.isViewAttached()) {
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
    public void setView(ProductView view) {
        this.pView = view;

    }


    private boolean isViewAttached() {
        return this.pView != null;
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
