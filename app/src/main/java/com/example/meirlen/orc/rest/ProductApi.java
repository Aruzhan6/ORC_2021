package com.example.meirlen.orc.rest;



import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.request.Filter;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ProductApi {


    @Headers("Content-Type: application/json")
    @POST(ApiMethods.PRODUCT_GET)
    Observable<APIResponse<List<Product>>> getProducts(@Body Filter filter);

}
