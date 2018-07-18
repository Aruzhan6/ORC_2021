package com.example.meirlen.orc.di.components;

import com.example.meirlen.orc.di.modules.CategoryModule;
import com.example.meirlen.orc.di.modules.ProductModule;
import com.example.meirlen.orc.di.scopes.CategoryScope;
import com.example.meirlen.orc.interactor.ProductInteractor;
import com.example.meirlen.orc.interactor.impl.CategoryInteractorImpl;
import com.example.meirlen.orc.interactor.impl.ProductInteractorImpl;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.view.fragment.CategoriesFragment;
import com.example.meirlen.orc.view.fragment.ChildCategoriesFragment;
import com.example.meirlen.orc.view.fragment.ProductFragment;

import dagger.Subcomponent;


@CategoryScope
@Subcomponent(modules = {ProductModule.class})
public interface ProductComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder productComponent(ProductModule module);
        ProductComponent build();
    }

    ProductInteractorImpl inject(ProductInteractorImpl interactor);
    ProductFragment inject(ProductFragment fragment);

}
