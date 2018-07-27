package com.example.meirlen.orc.di.modules;

import com.example.meirlen.orc.di.scopes.BasketScope;
import com.example.meirlen.orc.di.scopes.DiscountScope;
import com.example.meirlen.orc.interactor.BasketInteractor;
import com.example.meirlen.orc.interactor.DiscountInteractor;
import com.example.meirlen.orc.interactor.impl.BasketInteractorImpl;
import com.example.meirlen.orc.interactor.impl.DiscountInteractorImpl;
import com.example.meirlen.orc.presenter.BasketPresenter;
import com.example.meirlen.orc.presenter.DiscountPresenter;
import com.example.meirlen.orc.presenter.impl.BasketPresenterImpl;
import com.example.meirlen.orc.presenter.impl.DiscountPresenterImpl;
import com.example.meirlen.orc.rest.BasketApi;
import com.example.meirlen.orc.rest.DiscountApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class DiscountModule {

    @DiscountScope
    @Provides
    DiscountInteractor provideInteractor() {
        return new DiscountInteractorImpl();
    }

    @DiscountScope
    @Provides
    DiscountPresenter providePresenter(DiscountInteractor interactor) {
        return new DiscountPresenterImpl(interactor);
    }

    @DiscountScope
    @Provides
    DiscountApi provideApiService(Retrofit retrofit) {
        return retrofit.create(DiscountApi.class);
    }
}
