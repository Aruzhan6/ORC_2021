package com.example.meirlen.orc.di.modules;

import android.content.Context;

import com.example.meirlen.orc.di.scopes.ChatScope;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private Context context;


    public AppModule(Context context) {
        this.context=context;
    }

    @Singleton
    @Provides
    Context provideContext(){
        return context;
    }


}
