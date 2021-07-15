package com.pixel.meirlen.orc.di.components;

import com.pixel.meirlen.orc.di.modules.BasketModule;
import com.pixel.meirlen.orc.di.scopes.BasketScope;
import com.pixel.meirlen.orc.interactor.impl.BasketInteractorImpl;
import com.pixel.meirlen.orc.view.fragment.BasketFragment;

import dagger.Subcomponent;


@BasketScope
@Subcomponent(modules = {BasketModule.class})
public interface BasketComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder busketComponent(BasketModule module);

        BasketComponent build();
    }

    BasketInteractorImpl inject(BasketInteractorImpl interactor);

    BasketFragment inject(BasketFragment fragment);



}
