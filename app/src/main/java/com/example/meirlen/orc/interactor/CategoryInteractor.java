package com.example.meirlen.orc.interactor;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.rest.model.Category;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface CategoryInteractor {

    Observable<APIResponse<List<Category>>> getMessages(String authToken);
    Flowable<List<Category>> getLocalCategories();
    Flowable<List<Category>> getChildCategories(String id);
    Completable insertCategories(List<Category> categories);


}
