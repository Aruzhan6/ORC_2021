package com.example.meirlen.orc.rest;



import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.rest.ApiMethods;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CategoryApi {

    @GET (ApiMethods.CHAT_GET)
    Observable<APIResponse<List<Category>>> getChat(@Header("Token") String token);

}
