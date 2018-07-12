package com.example.meirlen.orc.rest.api;



import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.rest.api.ApiMethods;
import com.example.meirlen.orc.rest.model.Category;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface CategoryApi {


    @GET (ApiMethods.CHAT_GET)
    Observable<APIResponse<List<Category>>> getChat(@Header("auth-token") String token);

}
