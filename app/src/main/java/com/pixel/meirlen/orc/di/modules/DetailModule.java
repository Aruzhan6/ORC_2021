package com.pixel.meirlen.orc.di.modules;

import com.pixel.meirlen.orc.di.scopes.ProductScope;
import com.pixel.meirlen.orc.interactor.ProductInteractor;
import com.pixel.meirlen.orc.interactor.impl.ProductInteractorImpl;
import com.pixel.meirlen.orc.presenter.ProductPresenter;
import com.pixel.meirlen.orc.presenter.impl.DetailPresenterImpl;
import com.pixel.meirlen.orc.presenter.impl.PoductPresenterImpl;
import com.pixel.meirlen.orc.rest.ProductApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class DetailModule {

    @ProductScope
    @Provides
    ProductInteractor provideInteractor() {
        return new ProductInteractorImpl();
    }

    @ProductScope
    @Provides
    ProductPresenter providePresenter(ProductInteractor interactor) {
        return new DetailPresenterImpl(interactor);
    }

    @ProductScope
    @Provides
    ProductApi provideApiService(Retrofit retrofit) {
        return retrofit.create(ProductApi.class);
    }
}
