package com.pixel.meirlen.orc.di.modules;

import com.pixel.meirlen.orc.di.scopes.BasketScope;
import com.pixel.meirlen.orc.interactor.BasketInteractor;
import com.pixel.meirlen.orc.interactor.OrderInteractor;
import com.pixel.meirlen.orc.interactor.impl.BasketInteractorImpl;
import com.pixel.meirlen.orc.interactor.impl.OrderInteractorImpl;
import com.pixel.meirlen.orc.presenter.BasketPresenter;
import com.pixel.meirlen.orc.presenter.OrderPresenter;
import com.pixel.meirlen.orc.presenter.impl.BasketPresenterImpl;
import com.pixel.meirlen.orc.presenter.impl.OrderPresenterImpl;
import com.pixel.meirlen.orc.rest.BasketApi;
import com.pixel.meirlen.orc.rest.OrderApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class OrderModule {

    @BasketScope
    @Provides
    OrderInteractor provideInteractor() {
        return new OrderInteractorImpl();
    }

    @BasketScope
    @Provides
    OrderPresenter providePresenter(OrderInteractor interactor) {
        return new OrderPresenterImpl(interactor);
    }

    @BasketScope
    @Provides
    OrderApi provideApiService(Retrofit retrofit) {
        return retrofit.create(OrderApi.class);
    }
}
