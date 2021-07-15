package com.pixel.meirlen.orc.di.components;

import com.pixel.meirlen.orc.di.modules.QrModule;
import com.pixel.meirlen.orc.di.scopes.ProductScope;
import com.pixel.meirlen.orc.interactor.impl.QrInteractorImpl;
import com.pixel.meirlen.orc.view.fragment.QrListFragment;

import dagger.Subcomponent;


@ProductScope
@Subcomponent(modules = {QrModule.class})
public interface QRComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder productComponent(QrModule module);
        QRComponent build();
    }

    QrInteractorImpl inject(QrInteractorImpl fragment);
    QrListFragment inject(QrListFragment fragment);

}
