package com.example.meirlen.orc.di.modules;

import com.example.meirlen.orc.di.scopes.ProductScope;
import com.example.meirlen.orc.interactor.QrInteractor;
import com.example.meirlen.orc.interactor.impl.QrInteractorImpl;
import com.example.meirlen.orc.presenter.QrPresenter;
import com.example.meirlen.orc.presenter.impl.QrPresenterImpl;
import com.example.meirlen.orc.rest.ProductApi;
import com.example.meirlen.orc.rest.QRApi;

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
