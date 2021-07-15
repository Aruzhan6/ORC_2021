package com.pixel.meirlen.orc.di.modules;

import com.pixel.meirlen.orc.di.scopes.CategoryScope;
import com.pixel.meirlen.orc.di.scopes.FieldScope;
import com.pixel.meirlen.orc.interactor.CategoryInteractor;
import com.pixel.meirlen.orc.interactor.impl.CategoryInteractorImpl;
import com.pixel.meirlen.orc.presenter.CategoryPresenter;
import com.pixel.meirlen.orc.presenter.FieldPresenter;
import com.pixel.meirlen.orc.presenter.impl.CategoryPresenterImpl;
import com.pixel.meirlen.orc.presenter.impl.FieldPresenterImpl;
import com.pixel.meirlen.orc.rest.CategoryApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class FieldModule {

    @FieldScope
    @Provides
    CategoryInteractor provideChatInteractor() {
        return new CategoryInteractorImpl();
    }

    @FieldScope
    @Provides
    FieldPresenter provideChatPresenter(CategoryInteractor conversationInteractor) {
        return new FieldPresenterImpl(conversationInteractor);
    }

    @FieldScope
    @Provides
    CategoryApi provideApiService(Retrofit retrofit) {
        return retrofit.create(CategoryApi.class);
    }
}
