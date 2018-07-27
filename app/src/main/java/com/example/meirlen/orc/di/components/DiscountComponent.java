package com.example.meirlen.orc.di.components;

import com.example.meirlen.orc.di.modules.DiscountModule;
import com.example.meirlen.orc.di.scopes.DiscountScope;
import com.example.meirlen.orc.interactor.impl.DiscountInteractorImpl;
import com.example.meirlen.orc.view.fragment.DiscountFragment;
import com.example.meirlen.orc.view.fragment.HistoryFragment;

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
