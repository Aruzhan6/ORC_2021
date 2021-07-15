package com.pixel.meirlen.orc.di.components;

import com.pixel.meirlen.orc.MainActivity;
import com.pixel.meirlen.orc.di.modules.BasketModule;
import com.pixel.meirlen.orc.di.modules.MaintModule;
import com.pixel.meirlen.orc.di.scopes.BasketScope;
import com.pixel.meirlen.orc.di.scopes.MainScope;
import com.pixel.meirlen.orc.interactor.impl.BasketInteractorImpl;
import com.pixel.meirlen.orc.view.fragment.BasketFragment;

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
