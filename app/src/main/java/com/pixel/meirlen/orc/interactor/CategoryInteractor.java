package com.pixel.meirlen.orc.interactor;


import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.model.CartCount;
import com.pixel.meirlen.orc.model.Category;
import com.pixel.meirlen.orc.model.Field;
import com.pixel.meirlen.orc.model.SearchValue;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface CategoryInteractor {

    Observable<APIResponse<List<Category>>> getMessages(String authToken);
    Observable<APIResponse<CartCount>> getCartCount(String authToken);
    Flowable<List<Category>> getLocalCategories();
    Flowable<List<Category>> getChildCategories(String id);
    Flowable<List<Field>> getFieldById(String id);
    Flowable<List<SearchValue>> getFieldValueById(String id);
    Completable insertCategories(List<Category> categories);
    Completable updateSearchValue(SearchValue searchValue);


}
