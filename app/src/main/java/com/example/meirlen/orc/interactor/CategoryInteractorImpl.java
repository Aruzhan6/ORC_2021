package com.example.meirlen.orc.interactor;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.AppDatabase;
import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.helper.DataGenerator;
import com.example.meirlen.orc.rest.model.Category;
import com.example.meirlen.orc.rest.api.CategoryApi;

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
        App.getInstance().createChatComponent().inject(this);
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
    public Completable insertCategories(List<Category> categories) {
        dataGenerator.generateChildCategory(categories);
        return Completable.fromAction(() -> appDatabase.categoryDao().insert(categories));
    }


}
