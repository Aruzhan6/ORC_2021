package com.example.meirlen.orc;

import android.app.Application;

import com.example.meirlen.orc.di.components.AppComponent;
import com.example.meirlen.orc.di.components.CategoryComponent;
import com.example.meirlen.orc.di.components.DaggerAppComponent;
import com.example.meirlen.orc.di.modules.AppModule;


public class App extends Application {

    private static final String TAG = "App";

    public static App instance;
    private AppComponent appComponent;
    private CategoryComponent categoryComponent;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(instance,"http://orc.workapp.kz"))
                .build();

    }

    public CategoryComponent createChatComponent() {
        if (categoryComponent == null) {
            categoryComponent = appComponent.chatBuilder().build();
        }

        return categoryComponent;
    }




}
