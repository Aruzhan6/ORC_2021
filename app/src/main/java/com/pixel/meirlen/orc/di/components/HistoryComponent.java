package com.pixel.meirlen.orc.di.components;

import com.pixel.meirlen.orc.di.modules.BasketModule;
import com.pixel.meirlen.orc.di.scopes.BasketScope;
import com.pixel.meirlen.orc.interactor.impl.BasketInteractorImpl;
import com.pixel.meirlen.orc.view.fragment.BasketFragment;
import com.pixel.meirlen.orc.view.fragment.HistoryFragment;

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
