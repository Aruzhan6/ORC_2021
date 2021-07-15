package com.pixel.meirlen.orc.di.components;

import com.pixel.meirlen.orc.di.modules.BasketModule;
import com.pixel.meirlen.orc.di.modules.OrderModule;
import com.pixel.meirlen.orc.di.scopes.BasketScope;
import com.pixel.meirlen.orc.interactor.impl.BasketInteractorImpl;
import com.pixel.meirlen.orc.interactor.impl.OrderInteractorImpl;
import com.pixel.meirlen.orc.view.fragment.BasketFragment;
import com.pixel.meirlen.orc.view.fragment.FieldFragment;
import com.pixel.meirlen.orc.view.fragment.HistoryFragment;
import com.pixel.meirlen.orc.view.fragment.OrderFragment;

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
