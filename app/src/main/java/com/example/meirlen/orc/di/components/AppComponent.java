package com.example.meirlen.orc.di.components;

import com.example.meirlen.orc.di.modules.AppModule;
import com.example.meirlen.orc.model.Field;
import com.example.meirlen.orc.model.Product;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {


    CategoryComponent.Builder categoryBuilder();
    ProductComponent.Builder productBuilder();
    FieldComponent.Builder  fieldBuilder();
    SignUpComponent.Builder  signUpComponentBuilder();


}
