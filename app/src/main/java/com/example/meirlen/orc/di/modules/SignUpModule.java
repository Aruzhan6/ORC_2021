package com.example.meirlen.orc.di.modules;

import com.example.meirlen.orc.di.scopes.CategoryScope;
import com.example.meirlen.orc.di.scopes.SignUpScope;
import com.example.meirlen.orc.interactor.ProductInteractor;
import com.example.meirlen.orc.interactor.SignUpInteractor;
import com.example.meirlen.orc.interactor.impl.ProductInteractorImpl;
import com.example.meirlen.orc.interactor.impl.SignUpInteractorImpl;
import com.example.meirlen.orc.presenter.ProductPresenter;
import com.example.meirlen.orc.presenter.SignUpPresenter;
import com.example.meirlen.orc.presenter.impl.PoductPresenterImpl;
import com.example.meirlen.orc.presenter.impl.SignUpPresenterImpl;
import com.example.meirlen.orc.rest.ProductApi;
import com.example.meirlen.orc.rest.SignApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class SignUpModule {

    @SignUpScope
    @Provides
    SignUpInteractor provideInteractor() {
        return new SignUpInteractorImpl();
    }

    @SignUpScope
    @Provides
    SignUpPresenter providePresenter(SignUpInteractor interactor) {
        return new SignUpPresenterImpl(interactor);
    }

    @SignUpScope
    @Provides
    SignApi provideApiService(Retrofit retrofit) {
        return retrofit.create(SignApi.class);
    }
}
