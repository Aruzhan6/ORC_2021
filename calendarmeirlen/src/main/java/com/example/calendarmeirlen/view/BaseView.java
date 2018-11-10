package com.example.calendarmeirlen.view;

import android.support.annotation.LayoutRes;

import com.example.calendarmeirlen.interactors.ViewInteractor;
import com.example.calendarmeirlen.presenter.interfeaces.CustomizableCalendarPresenter;


public interface BaseView {
    void refreshData();

    void setLayoutResId(@LayoutRes int layoutResId);

    void injectViewInteractor(ViewInteractor viewInteractor);

    void injectPresenter(CustomizableCalendarPresenter presenter);
}