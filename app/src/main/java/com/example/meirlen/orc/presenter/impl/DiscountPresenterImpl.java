package com.example.meirlen.orc.presenter.impl;

import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.api.NetworkResponse;
import com.example.meirlen.orc.interactor.DiscountInteractor;
import com.example.meirlen.orc.interactor.OrderInteractor;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.discount.Discount;
import com.example.meirlen.orc.presenter.DiscountPresenter;
import com.example.meirlen.orc.presenter.OrderPresenter;
import com.example.meirlen.orc.view.DiscountView;
import com.example.meirlen.orc.view.OrderView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class DiscountPresenterImpl implements DiscountPresenter {

    public DiscountInteractor interactor;
    public DiscountView pView;
    private Disposable getMessagesDisposable;


    public DiscountPresenterImpl(DiscountInteractor interactor) {
        this.interactor = interactor;
    }


    @Override
    public void getList() {

        pView.showLoading();
        getMessagesDisposable = interactor.getList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse<List<Discount>> apiResponse) -> {
                    pView.hideLoading();
                    if (apiResponse.getIsSuccess() && apiResponse.getStatus() == NetworkResponse.CODE_OK) {
                        if (this.isViewAttached()) {
                            pView.getList(apiResponse.getData());
                        }
                    } else {
                        if (this.isViewAttached()) {
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
    public void setView(DiscountView view) {
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
