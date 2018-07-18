package com.example.meirlen.orc.interactor;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.request.Filter;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface ProductInteractor {

    Observable<APIResponse<List<Product>>> getList(Filter filter);


}
