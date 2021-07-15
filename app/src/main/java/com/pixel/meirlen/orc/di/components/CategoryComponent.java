package com.pixel.meirlen.orc.di.components;

import com.pixel.meirlen.orc.di.modules.CategoryModule;
import com.pixel.meirlen.orc.di.scopes.CategoryScope;
import com.pixel.meirlen.orc.interactor.impl.CategoryInteractorImpl;
import com.pixel.meirlen.orc.view.activity.IntroActivity;
import com.pixel.meirlen.orc.view.activity.SplashActivity;
import com.pixel.meirlen.orc.view.fragment.CategoriesFragment;
import com.pixel.meirlen.orc.view.fragment.ChildCategoriesFragment;

import dagger.Subcomponent;


@CategoryScope
@Subcomponent(modules = {CategoryModule.class})
public interface CategoryComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder categoryComponent(CategoryModule module);
        CategoryComponent build();
    }

    IntroActivity inject(IntroActivity activity);
    SplashActivity inject(SplashActivity activity);
    CategoriesFragment inject(CategoriesFragment fragment);
    ChildCategoriesFragment inject(ChildCategoriesFragment fragment);
    CategoryInteractorImpl inject(CategoryInteractorImpl interactor);
}
