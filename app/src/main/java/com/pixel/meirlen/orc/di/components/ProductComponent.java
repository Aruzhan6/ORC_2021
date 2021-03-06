package com.pixel.meirlen.orc.di.components;

import com.pixel.meirlen.orc.di.modules.CategoryModule;
import com.pixel.meirlen.orc.di.modules.ProductModule;
import com.pixel.meirlen.orc.di.scopes.CategoryScope;
import com.pixel.meirlen.orc.di.scopes.ProductScope;
import com.pixel.meirlen.orc.interactor.ProductInteractor;
import com.pixel.meirlen.orc.interactor.impl.CategoryInteractorImpl;
import com.pixel.meirlen.orc.interactor.impl.ProductInteractorImpl;
import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.view.activity.ProductListActivity;
import com.pixel.meirlen.orc.view.fragment.CategoriesFragment;
import com.pixel.meirlen.orc.view.fragment.ChildCategoriesFragment;
import com.pixel.meirlen.orc.view.fragment.ProductFragment;

import dagger.Subcomponent;


@ProductScope
@Subcomponent(modules = {ProductModule.class})
public interface ProductComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder productComponent(ProductModule module);
        ProductComponent build();
    }

    ProductInteractorImpl inject(ProductInteractorImpl interactor);
    ProductListActivity inject(ProductListActivity activity);
    ProductFragment inject(ProductFragment fragment);

}
