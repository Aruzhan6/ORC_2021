package com.pixel.meirlen.orc.di.components;

import com.pixel.meirlen.orc.di.modules.ProductModule;
import com.pixel.meirlen.orc.di.scopes.ProductScope;
import com.pixel.meirlen.orc.view.fragment.DetailFragment;

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
