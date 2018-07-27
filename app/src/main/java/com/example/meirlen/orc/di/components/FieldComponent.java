package com.example.meirlen.orc.di.components;

import com.example.meirlen.orc.di.modules.CategoryModule;
import com.example.meirlen.orc.di.modules.FieldModule;
import com.example.meirlen.orc.di.scopes.CategoryScope;
import com.example.meirlen.orc.di.scopes.FieldScope;
import com.example.meirlen.orc.interactor.impl.CategoryInteractorImpl;
import com.example.meirlen.orc.model.Field;
import com.example.meirlen.orc.view.fragment.CategoriesFragment;
import com.example.meirlen.orc.view.fragment.ChildCategoriesFragment;
import com.example.meirlen.orc.view.fragment.FieldFragment;

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
