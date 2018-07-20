package com.example.meirlen.orc.presenter.impl;

import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.api.NetworkResponse;
import com.example.meirlen.orc.interactor.SignUpInteractor;

import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.signup.ConfirmResponse;
import com.example.meirlen.orc.model.signup.SignupRequest;
import com.example.meirlen.orc.presenter.CategoryPresenter;
import com.example.meirlen.orc.presenter.SignUpPresenter;
import com.example.meirlen.orc.view.SignUpView;
import com.example.meirlen.orc.view.fragment.ConfirmFragment;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


public class SignUpPresenterImpl implements SignUpPresenter {

    public SignUpInteractor interactor;
    public SignUpView mViews;
    private Disposable getMessagesDisposable;


    public SignUpPresenterImpl(SignUpInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void signUp(String number, String name, String city_id) {
        mViews.showLoading();

        getMessagesDisposable = interactor.signUp(number, name, city_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse apiResponse) -> {
                    mViews.hideLoading();
                    if (apiResponse.getIsSuccess() || apiResponse.getStatus() == NetworkResponse.CODE_OK || apiResponse.getStatus() == NetworkResponse.CREATED) {
                        if (SignUpPresenterImpl.this.isViewAttached()) {
                            mViews.response(apiResponse.getIsSuccess());
                        }
                    } else {
                        if (SignUpPresenterImpl.this.isViewAttached()) {
                            mViews.loadingFailed(String.valueOf(apiResponse.getMessage()));
                        }
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        mViews.hideLoading();
                        mViews.loadingFailed(throwable.getMessage());
                    }
                });
    }

    @Override
    public void signIn(String number) {
        mViews.showLoading();

        getMessagesDisposable = interactor.signIn(number)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse apiResponse) -> {
                    mViews.hideLoading();
                    if (apiResponse.getIsSuccess() || apiResponse.getStatus() == NetworkResponse.CODE_OK || apiResponse.getStatus() == NetworkResponse.CREATED) {
                        if (SignUpPresenterImpl.this.isViewAttached()) {
                            mViews.response(apiResponse.getIsSuccess());
                        }
                    } else {
                        if (SignUpPresenterImpl.this.isViewAttached()) {
                            mViews.loadingFailed(String.valueOf(apiResponse.getMessage()));
                        }
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        mViews.hideLoading();
                        mViews.loadingFailed(throwable.getMessage());
                    }
                });
    }

    @Override
    public void getProfile(String token) {
        getMessagesDisposable = interactor.get_profile(token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse<ConfirmResponse> apiResponse) -> {
                    mViews.hideLoading();
                    if (apiResponse.getIsSuccess() || apiResponse.getStatus() == NetworkResponse.CODE_OK || apiResponse.getStatus() == NetworkResponse.CREATED) {
                        if (SignUpPresenterImpl.this.isViewAttached()) {
                            mViews.response(apiResponse.getIsSuccess(), apiResponse.getData());
                        }
                    } else {
                        if (SignUpPresenterImpl.this.isViewAttached()) {
                            mViews.loadingFailed(String.valueOf(apiResponse.getMessage()));
                        }
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        mViews.hideLoading();
                        mViews.loadingFailed(throwable.getMessage());
                    }
                });
    }

    @Override
    public void confirm(String sms, String number) {
        mViews.showLoading();
        getMessagesDisposable = interactor.confirm(sms, number)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse<ConfirmResponse> apiResponse) -> {
                    mViews.hideLoading();
                    if (apiResponse.getIsSuccess() || apiResponse.getStatus() == NetworkResponse.CODE_OK || apiResponse.getStatus() == NetworkResponse.CREATED) {
                        if (SignUpPresenterImpl.this.isViewAttached()) {
                            mViews.response(apiResponse.getIsSuccess(), apiResponse.getData());
                        }
                    } else {
                        if (SignUpPresenterImpl.this.isViewAttached()) {
                            mViews.loadingFailed(String.valueOf(apiResponse.getMessage()));
                        }
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        mViews.hideLoading();
                        mViews.loadingFailed(throwable.getMessage());
                    }
                });


    }


    @Override
    public void setView(SignUpView view) {
        this.mViews = view;

    }


    private boolean isViewAttached() {
        return this.mViews != null;
    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }


    @Override
    public void destroy() {
        this.mViews = null;

    }


}
