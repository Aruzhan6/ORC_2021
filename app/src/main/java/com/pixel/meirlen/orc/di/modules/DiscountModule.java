package com.pixel.meirlen.orc.di.modules;

import com.pixel.meirlen.orc.di.scopes.BasketScope;
import com.pixel.meirlen.orc.di.scopes.DiscountScope;
import com.pixel.meirlen.orc.interactor.BasketInteractor;
import com.pixel.meirlen.orc.interactor.DiscountInteractor;
import com.pixel.meirlen.orc.interactor.impl.BasketInteractorImpl;
import com.pixel.meirlen.orc.interactor.impl.DiscountInteractorImpl;
import com.pixel.meirlen.orc.presenter.BasketPresenter;
import com.pixel.meirlen.orc.presenter.DiscountPresenter;
import com.pixel.meirlen.orc.presenter.impl.BasketPresenterImpl;
import com.pixel.meirlen.orc.presenter.impl.DiscountPresenterImpl;
import com.pixel.meirlen.orc.rest.BasketApi;
import com.pixel.meirlen.orc.rest.DiscountApi;

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
