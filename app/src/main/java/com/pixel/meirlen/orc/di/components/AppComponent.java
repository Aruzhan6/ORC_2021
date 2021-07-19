package com.pixel.meirlen.orc.di.components;

import com.pixel.meirlen.orc.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class})

public interface AppComponent {


    CategoryComponent.Builder categoryBuilder();

    ProductComponent.Builder productBuilder();

    FieldComponent.Builder fieldBuilder();

    SignUpComponent.Builder signUpComponentBuilder();

    BasketComponent.Builder basketComponentBuilder();

    OrderComponent.Builder orderComponentBuilder();

    HistoryComponent.Builder historyComponentBuilder();

    DiscountComponent.Builder discountComponentBuilder();

    DetailComponent.Builder detailComponentBuilder();

    MainComponent.Builder mainComponentBuilder();

    QRComponent.Builder qrComponentBuilder();

}
