package com.example.meirlen.orc.presenter.impl;

import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.api.NetworkResponse;
import com.example.meirlen.orc.interactor.ProductInteractor;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.ProductResponse;
import com.example.meirlen.orc.model.request.Filter;
import com.example.meirlen.orc.presenter.ProductPresenter;
import com.example.meirlen.orc.view.ProductView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.meirlen.orc.helper.Constans.COUNT_CHANGE;
import static com.example.meirlen.orc.helper.Constans.FAV_CHANGE;


public class SearchPresenterImpl implements ProductPresenter {

    public ProductInteractor interactor;
    public ProductView pView;
    private Disposable getMessagesDisposable;


    public SearchPresenterImpl(ProductInteractor interactor) {
        this.interactor = interactor;
    }


    @Override
    public void getList(String token, Filter filter) {
        pView.showLoading();
        getMessagesDisposable = interactor.getList(token, filter)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse<ProductResponse> apiResponse) -> {
                    pView.hideLoading();
                    if (apiResponse.getIsSuccess() && apiResponse.getStatus() == NetworkResponse.CODE_OK) {
                        if (SearchPresenterImpl.this.isViewAttached()) {
                            pView.getList(apiResponse.getData().getProduct());
                        }
                    } else {
                        if (SearchPresenterImpl.this.isViewAttached()) {
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
    public void getListById(String category_id, String token, Filter filter) {
        pView.showLoading();
        getMessagesDisposable = interactor.getCategoryId(category_id,token, filter)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse<ProductResponse> apiResponse) -> {
                    pView.hideLoading();
                    if (apiResponse.getIsSuccess() && apiResponse.getStatus() == NetworkResponse.CODE_OK) {
                        if (SearchPresenterImpl.this.isViewAttached()) {
                            pView.getList(apiResponse.getData().getProduct());
                        }
                    } else {
                        if (SearchPresenterImpl.this.isViewAttached()) {
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
    public void getProductById(String product_id, String token) {

    }

    @Override
    public void getProducerById(String category_id, String token, Filter filter) {

    }

    @Override
    public void getFavourities(String token) {
        pView.showLoading();
        getMessagesDisposable = interactor.getFavourities(token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse<ProductResponse> apiResponse) -> {
                    pView.hideLoading();
                    if (apiResponse.getIsSuccess() && apiResponse.getStatus() == NetworkResponse.CODE_OK) {
                        if (SearchPresenterImpl.this.isViewAttached()) {
                            pView.getList(apiResponse.getData().getProduct());
                        }
                    } else {
                        if (SearchPresenterImpl.this.isViewAttached()) {
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
        pView.showItemLoading(COUNT_CHANGE);
        getMessagesDisposable = interactor.addCart(token, id, decrement)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse<CardResponse> apiResponse) -> {
                    pView.hideItemLoading(COUNT_CHANGE);
                    if (apiResponse.getIsSuccess() && apiResponse.getStatus() == NetworkResponse.CODE_OK) {
                        if (this.isViewAttached()) {
                            pView.addCartResponse(apiResponse.getData());
                        }
                    } else {
                        if (this.isViewAttached()) {
                            pView.loadingFailed(String.valueOf(apiResponse.getMessage()));
                        }
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        pView.hideItemLoading(COUNT_CHANGE);
                        pView.loadingFailed(throwable.getMessage());
                    }
                });


    }

    @Override
    public void markFavourite(String token, Integer product_id) {
        pView.showItemLoading(FAV_CHANGE);
        getMessagesDisposable = interactor.markFavourite(token, product_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse<Product> apiResponse) -> {
                    pView.hideItemLoading(FAV_CHANGE);
                    if (apiResponse.getIsSuccess() && apiResponse.getStatus() == NetworkResponse.CODE_OK) {
                        if (this.isViewAttached()) {
                            pView.markFavourite(apiResponse.getData());
                        }
                    } else {
                        if (this.isViewAttached()) {
                            pView.loadingFailed(String.valueOf(apiResponse.getMessage()));
                        }
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        pView.hideItemLoading(FAV_CHANGE);
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
        getMessagesDisposable.dispose();
    }


}
