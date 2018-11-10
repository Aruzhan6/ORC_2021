package com.example.meirlen.orc.di.components;

import com.example.meirlen.orc.di.modules.ProductModule;
import com.example.meirlen.orc.di.scopes.ProductScope;
import com.example.meirlen.orc.interactor.impl.ProductInteractorImpl;
import com.example.meirlen.orc.view.fragment.DetailFragment;
import com.example.meirlen.orc.view.fragment.ProductFragment;

import dagger.Subcomponent;


@ProductScope
@Subcomponent(modules = {ProductModule.class})
public interface DetailComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder detailComponent(ProductModule module);
        DetailComponent build();
    }

    DetailFragment inject(DetailFragment fragment);

}
