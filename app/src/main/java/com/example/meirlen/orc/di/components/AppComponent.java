package com.example.meirlen.orc.di.components;

import com.example.meirlen.orc.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {


    CategoryComponent.Builder chatBuilder();


}
