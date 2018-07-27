package com.example.meirlen.orc.di.components;

import com.example.meirlen.orc.di.modules.BasketModule;
import com.example.meirlen.orc.di.modules.OrderModule;
import com.example.meirlen.orc.di.scopes.BasketScope;
import com.example.meirlen.orc.interactor.impl.BasketInteractorImpl;
import com.example.meirlen.orc.interactor.impl.OrderInteractorImpl;
import com.example.meirlen.orc.view.fragment.BasketFragment;
import com.example.meirlen.orc.view.fragment.FieldFragment;
import com.example.meirlen.orc.view.fragment.HistoryFragment;
import com.example.meirlen.orc.view.fragment.OrderFragment;

import dagger.Subcomponent;


@BasketScope
@Subcomponent(modules = {OrderModule.class})
public interface OrderComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder orderComponent(OrderModule module);

        OrderComponent build();
    }

    OrderInteractorImpl inject(OrderInteractorImpl interactor);
    OrderFragment inject(OrderFragment fragment);


}
