package com.example.meirlen.orc.di.components;

import com.example.meirlen.orc.di.modules.BasketModule;
import com.example.meirlen.orc.di.scopes.BasketScope;
import com.example.meirlen.orc.interactor.impl.BasketInteractorImpl;
import com.example.meirlen.orc.view.fragment.BasketFragment;
import com.example.meirlen.orc.view.fragment.HistoryFragment;

import dagger.Subcomponent;


@BasketScope
@Subcomponent(modules = {BasketModule.class})
public interface HistoryComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder busketComponent(BasketModule module);

        HistoryComponent build();
    }


    HistoryFragment inject(HistoryFragment fragment);


}
