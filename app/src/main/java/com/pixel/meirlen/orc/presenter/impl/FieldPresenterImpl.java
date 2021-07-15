package com.pixel.meirlen.orc.presenter.impl;

import android.util.Log;

import com.pixel.meirlen.orc.api.NetworkResponse;
import com.pixel.meirlen.orc.interactor.CategoryInteractor;
import com.pixel.meirlen.orc.model.Category;
import com.pixel.meirlen.orc.model.Field;
import com.pixel.meirlen.orc.model.SearchValue;
import com.pixel.meirlen.orc.presenter.CategoryPresenter;
import com.pixel.meirlen.orc.presenter.FieldPresenter;
import com.pixel.meirlen.orc.view.CategoryView;
import com.pixel.meirlen.orc.view.FieldView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

import static android.support.constraint.Constraints.TAG;


public class FieldPresenterImpl implements FieldPresenter {

    private static final String TAG = "FieldFragment";
    public CategoryInteractor categoryInteractor;

    public FieldView pView;
    private Disposable getMessagesDisposable;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

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

    @Override
    public void updateSearchValue(SearchValue searchValue) {
        pView.showLoading();

        mDisposable.add(categoryInteractor.updateSearchValue(searchValue)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                            pView.hideLoading();
                            pView.showMessage("ok");
                        },
                        throwable -> {
                            if (isViewAttached()) {
                                pView.hideLoading();
                                pView.loadingFailed(throwable.getMessage());
                            }
                        }));
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
        Log.d(TAG, "destroy: I'm a presenter, I was killed((");
        this.pView = null;
        mDisposable.dispose();
    }


}
