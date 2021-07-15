package com.pixel.meirlen.orc.di.modules;

import com.pixel.meirlen.orc.di.scopes.CategoryScope;
import com.pixel.meirlen.orc.di.scopes.SignUpScope;
import com.pixel.meirlen.orc.interactor.ProductInteractor;
import com.pixel.meirlen.orc.interactor.SignUpInteractor;
import com.pixel.meirlen.orc.interactor.impl.ProductInteractorImpl;
import com.pixel.meirlen.orc.interactor.impl.SignUpInteractorImpl;
import com.pixel.meirlen.orc.presenter.ProductPresenter;
import com.pixel.meirlen.orc.presenter.SignUpPresenter;
import com.pixel.meirlen.orc.presenter.impl.PoductPresenterImpl;
import com.pixel.meirlen.orc.presenter.impl.SignUpPresenterImpl;
import com.pixel.meirlen.orc.rest.ProductApi;
import com.pixel.meirlen.orc.rest.SignApi;

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
