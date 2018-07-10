package com.example.meirlen.orc.api;

import java.util.concurrent.Callable;

import io.reactivex.ObservableSource;

/**
 * Created by aaskarov on 13.11.2017.
 */

public class APIResponse<T> extends NetworkResponse implements Callable<ObservableSource<? extends T>> {

    public static final int ERROR_JSON = 1;
    public static final int ERROR_API = 2;

    public T object;
    public APIError apiError;

    public APIResponse(){}

    public APIResponse(NetworkResponse response) {
        error = response.error;
        code = response.code;
        exception = response.exception;
        responseString = response.responseString;
    }

    public APIResponse(APIResponse response) {
        this((NetworkResponse)response);
        apiError = response.apiError;
    }

    public boolean hasAPIError() {
        return apiError != null;
    }

    public boolean isCorrectResponse() {
        return error == NetworkResponse.NO_ERROR && code >= 200 && code < 300;
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "object=" + object +
                ", apiError=" + apiError +
                '}';
    }

    @Override
    public ObservableSource<? extends T> call() throws Exception {
        return null;
    }


}
