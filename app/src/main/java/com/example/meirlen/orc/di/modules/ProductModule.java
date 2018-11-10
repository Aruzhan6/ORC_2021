package com.example.meirlen.orc.di.modules;

import com.example.meirlen.orc.di.scopes.ProductScope;
import com.example.meirlen.orc.interactor.ProductInteractor;
import com.example.meirlen.orc.interactor.impl.ProductInteractorImpl;
import com.example.meirlen.orc.presenter.ProductPresenter;
import com.example.meirlen.orc.presenter.impl.PoductPresenterImpl;
import com.example.meirlen.orc.rest.ProductApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class ProductModule {

    @ProductScope
    @Provides
    ProductInteractor provideInteractor() {
        return new ProductInteractorImpl();
    }

    @ProductScope
    @Provides
    ProductPresenter providePresenter(ProductInteractor interactor) {
        return new PoductPresenterImpl(interactor);
    }

    @ProductScope
    @Provides
    ProductApi provideApiService(Retrofit retrofit) {
        return retrofit.create(ProductApi.class);
    }
}
