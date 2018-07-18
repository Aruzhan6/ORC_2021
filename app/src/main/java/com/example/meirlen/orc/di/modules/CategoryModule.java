package com.example.meirlen.orc.di.modules;

import com.example.meirlen.orc.di.scopes.CategoryScope;
import com.example.meirlen.orc.interactor.CategoryInteractor;
import com.example.meirlen.orc.interactor.impl.CategoryInteractorImpl;
import com.example.meirlen.orc.presenter.CategoryPresenter;
import com.example.meirlen.orc.presenter.impl.CategoryPresenterImpl;
import com.example.meirlen.orc.rest.CategoryApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class CategoryModule {

    @CategoryScope
    @Provides
    CategoryInteractor provideChatInteractor() {
        return new CategoryInteractorImpl();
    }

    @CategoryScope
    @Provides
    CategoryPresenter provideChatPresenter(CategoryInteractor conversationInteractor) {
        return new CategoryPresenterImpl(conversationInteractor);
    }

    @CategoryScope
    @Provides
    CategoryApi provideApiService(Retrofit retrofit) {
        return retrofit.create(CategoryApi.class);
    }
}
