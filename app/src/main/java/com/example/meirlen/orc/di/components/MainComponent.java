package com.example.meirlen.orc.di.components;

import com.example.meirlen.orc.MainActivity;
import com.example.meirlen.orc.di.modules.BasketModule;
import com.example.meirlen.orc.di.modules.MaintModule;
import com.example.meirlen.orc.di.scopes.BasketScope;
import com.example.meirlen.orc.di.scopes.MainScope;
import com.example.meirlen.orc.interactor.impl.BasketInteractorImpl;
import com.example.meirlen.orc.view.fragment.BasketFragment;

import dagger.Subcomponent;


@MainScope
@Subcomponent(modules = {MaintModule.class})
public interface MainComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder busketComponent(MaintModule module);

        MainComponent build();
    }


    MainActivity inject(MainActivity mainActivity);



}
