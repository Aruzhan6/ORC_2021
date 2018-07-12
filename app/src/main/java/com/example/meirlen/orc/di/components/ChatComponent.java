package com.example.meirlen.orc.di.components;

import com.example.meirlen.orc.MainActivity;
import com.example.meirlen.orc.di.modules.ChatModule;
import com.example.meirlen.orc.di.scopes.ChatScope;
import com.example.meirlen.orc.interactor.ChatInteractor;
import com.example.meirlen.orc.interactor.ChatInteractorImpl;
import com.example.meirlen.orc.view.fragment.CategoriesFragment;

import dagger.Subcomponent;


@ChatScope
@Subcomponent(modules = {ChatModule.class})
public interface ChatComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder chatComponent(ChatModule module);
        ChatComponent build();
    }

    CategoriesFragment inject(CategoriesFragment fragment);
    ChatInteractorImpl inject(ChatInteractorImpl chatInteractor);
}
