package com.example.meirlen.orc;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;

import com.example.meirlen.orc.di.components.AppComponent;
import com.example.meirlen.orc.di.components.ChatComponent;
import com.example.meirlen.orc.di.components.DaggerAppComponent;
import com.example.meirlen.orc.di.modules.AppModule;


public class App extends Application {

    private static final String TAG = "App";

    public static App instance;

    public static LocalBroadcastManager BROADCAST_MANAGER;
    private static volatile boolean applicationInited = false;

    private AppComponent appComponent;
    private ChatComponent chatComponent;



    public static App getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();


        instance = this;

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();



    }

    public static Context getAppContext() {
        return instance;
    }



    public AppComponent getAppComponent() {
        return appComponent;
    }


    public ChatComponent createChatComponent() {
        if (chatComponent == null) {
            chatComponent = appComponent.chatBuilder().build();
        }

        return chatComponent;
    }

    public void clearChatComponent() {
        chatComponent = null;
    }



}
