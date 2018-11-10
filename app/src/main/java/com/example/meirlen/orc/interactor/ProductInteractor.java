package com.example.meirlen.orc.interactor;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.CardResponse;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.DetailResponse;
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

    Observable<APIResponse<ProductResponse>> getCategoryId(String id, String token, Filter filter);

    Observable<APIResponse<DetailResponse>> getProductById(String id, String token);

    Observable<APIResponse<ProductResponse>> getProducerById(String id,String token, Filter filter);

    Observable<APIResponse<ProductResponse>> getFavourities(String token);

    Observable<APIResponse<CardResponse>> addCart(String token, String id, String decrement);

    Observable<APIResponse<Product>> markFavourite(String token, Integer product_id);


}
