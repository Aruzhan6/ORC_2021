package com.example.meirlen.orc.di.modules;

import com.example.meirlen.orc.di.scopes.ChatScope;
import com.example.meirlen.orc.interactor.ChatInteractor;
import com.example.meirlen.orc.interactor.ChatInteractorImpl;
import com.example.meirlen.orc.presenter.ChatPresenter;
import com.example.meirlen.orc.presenter.impl.ChatPresenterImpl;
import com.example.meirlen.orc.rest.api.CategoryApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class ChatModule {

    @ChatScope
    @Provides
    ChatInteractor provideChatInteractor() {
        return new ChatInteractorImpl();
    }

    @ChatScope
    @Provides
    ChatPresenter provideChatPresenter(ChatInteractor conversationInteractor) {
        return new ChatPresenterImpl(conversationInteractor);
    }

    @ChatScope
    @Provides
    CategoryApi provideApiService(Retrofit retrofit) {
        return retrofit.create(CategoryApi.class);
    }
}
