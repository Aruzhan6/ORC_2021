package com.example.meirlen.orc.interactor.impl;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.AppDatabase;
import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.helper.DataGenerator;
import com.example.meirlen.orc.interactor.CategoryInteractor;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.Field;
import com.example.meirlen.orc.model.SearchValue;
import com.example.meirlen.orc.rest.CategoryApi;

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
        return categoryApi.getChat(authToken);
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
        return appDatabase.fieldDao().getAll();
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


}
