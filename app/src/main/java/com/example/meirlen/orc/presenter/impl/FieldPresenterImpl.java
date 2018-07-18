package com.example.meirlen.orc.presenter.impl;

import android.util.Log;

import com.example.meirlen.orc.api.NetworkResponse;
import com.example.meirlen.orc.interactor.CategoryInteractor;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.Field;
import com.example.meirlen.orc.model.SearchValue;
import com.example.meirlen.orc.presenter.CategoryPresenter;
import com.example.meirlen.orc.presenter.FieldPresenter;
import com.example.meirlen.orc.view.CategoryView;
import com.example.meirlen.orc.view.FieldView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


public class FieldPresenterImpl implements FieldPresenter {

    public CategoryInteractor categoryInteractor;
    public FieldView pView;
    private Disposable getMessagesDisposable;


    public FieldPresenterImpl(CategoryInteractor categoryInteractor) {
        this.categoryInteractor = categoryInteractor;
    }


    @Override
    public void setView(FieldView view) {
        this.pView = view;

    }

    @Override
    public void getFields(String id) {
        pView.showLoading();
        getMessagesDisposable = categoryInteractor.getFieldById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((List<Field> apiResponse) -> {

                    pView.hideLoading();
                    pView.getList(apiResponse);

                }, throwable -> {
                    if (isViewAttached()) {
                        pView.hideLoading();
                        pView.loadingFailed(throwable.getMessage());
                    }
                });

    }

    @Override
    public void getFieldValues(String id) {
        pView.showLoading();
        getMessagesDisposable = categoryInteractor.getFieldValueById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((List<SearchValue> apiResponse) -> {

                    pView.hideLoading();
                    pView.getFieldValues(apiResponse);

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
    public void resume() {

    }

    @Override
    public void pause() {
        getMessagesDisposable.dispose();
    }

    @Override
    public void destroy() {
        //this.pView = null;

    }


}
