package com.example.meirlen.orc.di.components;

import com.example.meirlen.orc.di.modules.BasketModule;
import com.example.meirlen.orc.di.modules.ProductModule;
import com.example.meirlen.orc.di.scopes.BasketScope;
import com.example.meirlen.orc.di.scopes.CategoryScope;
import com.example.meirlen.orc.interactor.BasketInteractor;
import com.example.meirlen.orc.interactor.impl.BasketInteractorImpl;
import com.example.meirlen.orc.interactor.impl.ProductInteractorImpl;
import com.example.meirlen.orc.model.basket.Basket;
import com.example.meirlen.orc.model.history.History;
import com.example.meirlen.orc.view.fragment.BasketFragment;
import com.example.meirlen.orc.view.fragment.HistoryFragment;
import com.example.meirlen.orc.view.fragment.ProductFragment;

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

    HistoryFragment inject(HistoryFragment fragment);


}
