package com.example.meirlen.orc.interactor;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.ProductResponse;
import com.example.meirlen.orc.model.request.CartRequest;
import com.example.meirlen.orc.model.request.Filter;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Header;

public interface ProductInteractor {

    Observable<APIResponse<ProductResponse>> getList(String token, Filter filter);

    Observable<APIResponse<CardResponse>> addCart(String token, String id, String decrement);


}
