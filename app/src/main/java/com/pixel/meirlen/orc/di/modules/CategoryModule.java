package com.pixel.meirlen.orc.di.modules;

import com.pixel.meirlen.orc.di.scopes.CategoryScope;
import com.pixel.meirlen.orc.interactor.CategoryInteractor;
import com.pixel.meirlen.orc.interactor.impl.CategoryInteractorImpl;
import com.pixel.meirlen.orc.presenter.CategoryPresenter;
import com.pixel.meirlen.orc.presenter.impl.CategoryPresenterImpl;
import com.pixel.meirlen.orc.rest.CategoryApi;

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
