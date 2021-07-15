package com.pixel.meirlen.orc.rest;


import com.pixel.meirlen.orc.api.APIResponse;
import com.pixel.meirlen.orc.model.CardResponse;
import com.pixel.meirlen.orc.model.DetailResponse;
import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.model.ProductResponse;
import com.pixel.meirlen.orc.model.request.CartRequest;
import com.pixel.meirlen.orc.model.request.Filter;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductApi {


    @Headers("Content-Type: application/json")
    @POST(ApiMethods.PRODUCT_GET)
    Observable<APIResponse<ProductResponse>> getProducts(@Header("Token") String token, @Body Filter filter);

    @Headers("Content-Type: application/json")
    @POST(ApiMethods.PRODUCTS_GET_BY_ID_CATEGORY)
    Observable<APIResponse<ProductResponse>> getProductsById(@Path("id") String id,@Header("Token") String token, @Body Filter filter);


    @Headers("Content-Type: application/json")
    @GET(ApiMethods.PRODUCT_GET_BY_ID)
    Observable<APIResponse<DetailResponse>> getById( @Path("id") String id,@Header("Token") String token);


    @Headers("Content-Type: application/json")
    @POST(ApiMethods.PRODUCT_GET_BY_PRODUCER)
    Observable<APIResponse<ProductResponse>> getProducerProducts(@Query("producer") String id_producer, @Header("Token") String token,@Body Filter filter);
    //@Query("address") String address
    @Headers("Content-Type: application/json")
    @GET(ApiMethods.PRODUCT_GET_FAV)
    Observable<APIResponse<ProductResponse>> getFavourities(@Header("Token") String token);


    @Headers("Content-Type: application/json")
    @POST(ApiMethods.CART_ADD)
    Observable<APIResponse<CardResponse>> addCart(@Header("Token") String token, @Body CartRequest cartRequest);

    @FormUrlEncoded
    @POST(ApiMethods.PRODUCT_ADD_FAV)
    Observable<APIResponse<Product>> markFavourite(@Header("Token") String token, @Field("product_id") Integer product_id);

}
