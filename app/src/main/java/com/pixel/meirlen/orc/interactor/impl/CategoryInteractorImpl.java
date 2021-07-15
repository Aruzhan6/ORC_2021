package com.pixel.meirlen.orc.interactor.impl;

import com.pixel.meirlen.orc.App;
import com.pixel.meirlen.orc.AppDatabase;
import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.helper.DataGenerator;
import com.pixel.meirlen.orc.interactor.CategoryInteractor;
import com.pixel.meirlen.orc.model.CartCount;
import com.pixel.meirlen.orc.model.Category;
import com.pixel.meirlen.orc.model.Field;
import com.pixel.meirlen.orc.model.SearchValue;
import com.pixel.meirlen.orc.rest.CategoryApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;


public class CategoryInteractorImpl implements CategoryInteractor {


    @Inject
    CategoryApi categoryApi;

    @Inject
    AppDatabase appDatabase;

    @Inject
    DataGenerator dataGenerator;


    public CategoryInteractorImpl() {
        App.getInstance().createCategoryComponent().inject(this);
    }


    @Override
    public Observable<APIResponse<List<Category>>> getMessages(String authToken) {
        return categoryApi.getCategories(authToken);
    }

    @Override
    public Observable<APIResponse<CartCount>> getCartCount(String authToken) {
        return categoryApi.getCartCount(authToken);
    }

    @Override
    public Flowable<List<Category>> getLocalCategories() {
        return appDatabase.categoryDao().getAll();
    }

    @Override
    public Flowable<List<Category>> getChildCategories(String id) {
        return appDatabase.categoryDao().getById(id);
    }

    @Override
    public Flowable<List<Field>> getFieldById(String id) {
        return appDatabase.fieldDao().getById(id);
    }

    @Override
    public Flowable<List<SearchValue>> getFieldValueById(String id) {
        return appDatabase.fieldValueDao().getById(id);
    }

    @Override
    public Completable insertCategories(List<Category> categories) {
        dataGenerator.generateChildCategory(categories);
        return Completable.fromAction(() -> appDatabase.categoryDao().insert(categories));
    }

    @Override
    public Completable updateSearchValue(SearchValue searchValue) {
        return Completable.fromAction(() -> appDatabase.fieldValueDao().update(searchValue));
    }


}
