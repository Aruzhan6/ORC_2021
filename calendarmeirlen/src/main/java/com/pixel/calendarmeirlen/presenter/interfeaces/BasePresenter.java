package com.pixel.calendarmeirlen.presenter.interfeaces;

import android.support.annotation.LayoutRes;
import android.view.View;

import com.pixel.calendarmeirlen.interactors.ViewInteractor;
import com.pixel.calendarmeirlen.view.BaseView;


public interface BasePresenter<T extends BaseView> {
    void setView(T view);

    void onBindView(View rootView);

    void injectViewInteractor(ViewInteractor viewInteractor);

    void setLayoutResId(@LayoutRes int layoutResId);
}