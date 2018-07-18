package com.example.meirlen.orc.di.modules;

import com.example.meirlen.orc.di.scopes.CategoryScope;
import com.example.meirlen.orc.interactor.CategoryInteractor;
import com.example.meirlen.orc.interactor.ProductInteractor;
import com.example.meirlen.orc.interactor.impl.CategoryInteractorImpl;
import com.example.meirlen.orc.interactor.impl.ProductInteractorImpl;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.presenter.CategoryPresenter;
import com.example.meirlen.orc.presenter.ProductPresenter;
import com.example.meirlen.orc.presenter.impl.CategoryPresenterImpl;
import com.example.meirlen.orc.presenter.impl.PoductPresenterImpl;
import com.example.meirlen.orc.rest.CategoryApi;
import com.example.meirlen.orc.rest.ProductApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class ProductModule {

    @CategoryScope
    @Provides
    ProductInteractor provideInteractor() {
        return new ProductInteractorImpl();
    }

    @CategoryScope
    @Provides
    ProductPresenter providePresenter(ProductInteractor interactor) {
        return new PoductPresenterImpl(interactor);
    }

    @CategoryScope
    @Provides
    ProductApi provideApiService(Retrofit retrofit) {
        return retrofit.create(ProductApi.class);
    }
}
