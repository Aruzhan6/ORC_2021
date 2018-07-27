package com.example.meirlen.orc.presenter.impl;

import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.api.NetworkResponse;
import com.example.meirlen.orc.interactor.CategoryInteractor;
import com.example.meirlen.orc.presenter.CategoryPresenter;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.view.CategoryView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


public class CategoryPresenterImpl implements CategoryPresenter {

    public CategoryInteractor categoryInteractor;
    public CategoryView categoryView;
    private Disposable getMessagesDisposable;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public CategoryPresenterImpl(CategoryInteractor categoryInteractor) {
        this.categoryInteractor = categoryInteractor;
    }


    @Override
    public void getCategories(String token) {
        categoryView.showLoading();

        getMessagesDisposable = categoryInteractor.getMessages(token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((APIResponse<List<Category>> apiResponse) -> {
                    categoryView.hideLoading();
                    if (apiResponse.getIsSuccess() && apiResponse.getStatus() == NetworkResponse.CODE_OK) {
                        if (CategoryPresenterImpl.this.isViewAttached()) {
                            categoryView.getCategories(apiResponse.getData());
                        }
                    } else {
                        if (CategoryPresenterImpl.this.isViewAttached()) {
                            categoryView.loadingFailed(String.valueOf(apiResponse.getMessage()));
                        }
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        categoryView.hideLoading();
                        categoryView.loadingFailed(throwable.getMessage());
                    }
                });


    }

    @Override
    public void getCategoriesFromLocalDb() {
        categoryView.showLoading();

        getMessagesDisposable = categoryInteractor.getLocalCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((List<Category> apiResponse) -> {

                    categoryView.hideLoading();
                    categoryView.getCategories(apiResponse);

                }, throwable -> {
                    if (isViewAttached()) {
                        categoryView.hideLoading();
                        categoryView.loadingFailed("Error: "+throwable.getMessage());
                    }
                });

    }

    @Override
    public void getCategoriesById(String id) {

        categoryView.showLoading();

        getMessagesDisposable = categoryInteractor.getChildCategories(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((List<Category> apiResponse) -> {

                    categoryView.hideLoading();
                    categoryView.getCategories(apiResponse);

                }, throwable -> {
                    if (isViewAttached()) {
                        categoryView.hideLoading();
                        categoryView.loadingFailed("Error: "+throwable.getMessage());                    }
                });

    }

    @Override
    public void insertLocalDb(List<Category> categories) {
        categoryView.showLoading();

        mDisposable.add(categoryInteractor.insertCategories(categories)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                               @Override
                               public void run() throws Exception {
                                   categoryView.hideLoading();
                                   categoryView.successLocalDb("Элементы успешно добавлены в локальную бд");
                               }
                           },
                        throwable -> {
                            if (isViewAttached()) {
                                categoryView.hideLoading();
                                categoryView.loadingFailed(throwable.getMessage());
                            }
                        }));
    }


    @Override
    public void setView(CategoryView view) {
        this.categoryView = view;

    }


    private boolean isViewAttached() {
        return this.categoryView != null;
    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {
        mDisposable.clear();
    }

    @Override
    public void destroy() {
        this.categoryView = null;

    }


}
