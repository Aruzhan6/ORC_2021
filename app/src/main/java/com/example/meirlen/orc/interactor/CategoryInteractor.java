package com.example.meirlen.orc.interactor;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.Field;
import com.example.meirlen.orc.model.SearchValue;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface CategoryInteractor {

    Observable<APIResponse<List<Category>>> getMessages(String authToken);
    Flowable<List<Category>> getLocalCategories();
    Flowable<List<Category>> getChildCategories(String id);
    Flowable<List<Field>> getFieldById(String id);
    Flowable<List<SearchValue>> getFieldValueById(String id);
    Completable insertCategories(List<Category> categories);


}
