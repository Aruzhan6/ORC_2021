package com.pixel.meirlen.orc.di.components;

import com.pixel.meirlen.orc.di.modules.CategoryModule;
import com.pixel.meirlen.orc.di.modules.FieldModule;
import com.pixel.meirlen.orc.di.scopes.CategoryScope;
import com.pixel.meirlen.orc.di.scopes.FieldScope;
import com.pixel.meirlen.orc.interactor.impl.CategoryInteractorImpl;
import com.pixel.meirlen.orc.model.Field;
import com.pixel.meirlen.orc.view.fragment.CategoriesFragment;
import com.pixel.meirlen.orc.view.fragment.ChildCategoriesFragment;
import com.pixel.meirlen.orc.view.fragment.FieldFragment;

import dagger.Subcomponent;


@FieldScope
@Subcomponent(modules = {FieldModule.class})
public interface FieldComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder fieldModuleComponent(FieldModule module);
        FieldComponent build();
    }

    FieldFragment inject(FieldFragment fragment);
}
