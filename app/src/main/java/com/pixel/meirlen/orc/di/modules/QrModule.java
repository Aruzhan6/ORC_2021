package com.pixel.meirlen.orc.di.modules;

import com.pixel.meirlen.orc.di.scopes.ProductScope;
import com.pixel.meirlen.orc.interactor.QrInteractor;
import com.pixel.meirlen.orc.interactor.impl.QrInteractorImpl;
import com.pixel.meirlen.orc.presenter.QrPresenter;
import com.pixel.meirlen.orc.presenter.impl.QrPresenterImpl;
import com.pixel.meirlen.orc.rest.ProductApi;
import com.pixel.meirlen.orc.rest.QRApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class QrModule {

    @ProductScope
    @Provides
    QrInteractor provideInteractor() {
        return new QrInteractorImpl();
    }

    @ProductScope
    @Provides
    QrPresenter providePresenter(QrInteractor interactor) {
        return new QrPresenterImpl(interactor);
    }

    @ProductScope
    @Provides
    QRApi provideApiService(Retrofit retrofit) {
        return retrofit.create(QRApi.class);
    }
}
