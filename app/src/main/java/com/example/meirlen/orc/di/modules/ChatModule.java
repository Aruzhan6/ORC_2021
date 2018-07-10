package com.example.meirlen.orc.di.modules;

import android.content.Context;

import com.example.meirlen.orc.di.scopes.ChatScope;
import com.example.meirlen.orc.interactor.ChatInteractor;
import com.example.meirlen.orc.interactor.ChatInteractorImpl;
import com.example.meirlen.orc.presenter.ChatPresenter;
import com.example.meirlen.orc.presenter.impl.ChatPresenterImpl;

import dagger.Module;
import dagger.Provides;



@Module
public class ChatModule {

    @ChatScope
    @Provides
    ChatInteractor provideChatInteractor(Context context){
        return new ChatInteractorImpl(context);
    }

    @ChatScope
    @Provides
    ChatPresenter provideChatPresenter(ChatInteractor conversationInteractor){
        return new ChatPresenterImpl(conversationInteractor);
    }
}
