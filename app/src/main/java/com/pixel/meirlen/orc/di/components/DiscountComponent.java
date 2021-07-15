package com.pixel.meirlen.orc.di.components;

import com.pixel.meirlen.orc.di.modules.DiscountModule;
import com.pixel.meirlen.orc.di.scopes.DiscountScope;
import com.pixel.meirlen.orc.interactor.impl.DiscountInteractorImpl;
import com.pixel.meirlen.orc.view.fragment.DiscountFragment;
import com.pixel.meirlen.orc.view.fragment.HistoryFragment;

import dagger.Subcomponent;


@DiscountScope
@Subcomponent(modules = {DiscountModule.class})
public interface DiscountComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder busketComponent(DiscountModule module);

        DiscountComponent build();
    }

    DiscountInteractorImpl inject(DiscountInteractorImpl interactor);

    DiscountFragment inject(DiscountFragment fragment);



}
