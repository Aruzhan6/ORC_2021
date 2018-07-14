package com.example.meirlen.orc.di.components;

import com.example.meirlen.orc.di.modules.CategoryModule;
import com.example.meirlen.orc.di.scopes.CategoryScope;
import com.example.meirlen.orc.interactor.CategoryInteractorImpl;
import com.example.meirlen.orc.view.fragment.CategoriesFragment;
import com.example.meirlen.orc.view.fragment.ChildCategoriesFragment;

import dagger.Subcomponent;


@CategoryScope
@Subcomponent(modules = {CategoryModule.class})
public interface CategoryComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder chatComponent(CategoryModule module);
        CategoryComponent build();
    }

    CategoriesFragment inject(CategoriesFragment fragment);
    ChildCategoriesFragment inject(ChildCategoriesFragment fragment);
    CategoryInteractorImpl inject(CategoryInteractorImpl chatInteractor);
}
