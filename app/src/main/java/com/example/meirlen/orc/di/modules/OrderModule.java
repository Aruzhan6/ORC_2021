package com.example.meirlen.orc.di.modules;

import com.example.meirlen.orc.di.scopes.BasketScope;
import com.example.meirlen.orc.interactor.BasketInteractor;
import com.example.meirlen.orc.interactor.OrderInteractor;
import com.example.meirlen.orc.interactor.impl.BasketInteractorImpl;
import com.example.meirlen.orc.interactor.impl.OrderInteractorImpl;
import com.example.meirlen.orc.presenter.BasketPresenter;
import com.example.meirlen.orc.presenter.OrderPresenter;
import com.example.meirlen.orc.presenter.impl.BasketPresenterImpl;
import com.example.meirlen.orc.presenter.impl.OrderPresenterImpl;
import com.example.meirlen.orc.rest.BasketApi;
import com.example.meirlen.orc.rest.OrderApi;

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
