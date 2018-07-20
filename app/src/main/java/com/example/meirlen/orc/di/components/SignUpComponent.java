package com.example.meirlen.orc.di.components;

import com.example.meirlen.orc.di.modules.SignUpModule;
import com.example.meirlen.orc.di.scopes.CategoryScope;
import com.example.meirlen.orc.interactor.impl.SignUpInteractorImpl;
import com.example.meirlen.orc.view.fragment.ProfileFragment;
import com.example.meirlen.orc.view.fragment.SignInFragment;
import com.example.meirlen.orc.view.fragment.ConfirmFragment;
import com.example.meirlen.orc.view.fragment.SignUpFragment;

import dagger.Subcomponent;


@CategoryScope
@Subcomponent(modules = {SignUpModule.class})
public interface SignUpComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder signUpComponent(SignUpModule module);

        SignUpComponent build();
    }


    ConfirmFragment inject(ConfirmFragment fragment);

    SignInFragment inject(SignInFragment fragment);

    SignUpFragment inject(SignUpFragment fragment);

    ProfileFragment inject(ProfileFragment fragment);

    SignUpInteractorImpl inject(SignUpInteractorImpl interactor);

}
