package com.example.meirlen.orc.interactor;


import com.example.meirlen.orc.api.APIResponse;
import com.example.meirlen.orc.rest.model.Category;

import java.util.List;

import io.reactivex.Observable;

public interface ChatInteractor {

    Observable<APIResponse<List<Category>>> getMessages(String authToken);

}
