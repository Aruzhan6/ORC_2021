package com.example.meirlen.orc.di.components;

import com.example.meirlen.orc.di.modules.SignUpModule;
import com.example.meirlen.orc.di.scopes.SignUpScope;
import com.example.meirlen.orc.interactor.impl.SignUpInteractorImpl;
import com.example.meirlen.orc.view.fragment.ProfileFragment;
import com.example.meirlen.orc.view.fragment.SignInFragment;
import com.example.meirlen.orc.view.fragment.SmSFragment;
import com.example.meirlen.orc.view.fragment.SignUpFragment;

import dagger.Subcomponent;


@SignUpScope
@Subcomponent(modules = {SignUpModule.class})
public interface SignUpComponent {

    @Subcomponent.Builder
    interface Builder {
        Builder signUpComponent(SignUpModule module);

        SignUpComponent build();
    }


    SmSFragment inject(SmSFragment fragment);

    SignInFragment inject(SignInFragment fragment);

    SignUpFragment inject(SignUpFragment fragment);

    ProfileFragment inject(ProfileFragment fragment);

    SignUpInteractorImpl inject(SignUpInteractorImpl interactor);

}
