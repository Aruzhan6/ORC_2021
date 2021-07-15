package com.pixel.calendarmeirlen.view;

import android.support.annotation.LayoutRes;

import com.pixel.calendarmeirlen.interactors.ViewInteractor;
import com.pixel.calendarmeirlen.presenter.interfeaces.CustomizableCalendarPresenter;


public interface BaseView {
    void refreshData();

    void setLayoutResId(@LayoutRes int layoutResId);

    void injectViewInteractor(ViewInteractor viewInteractor);

    void injectPresenter(CustomizableCalendarPresenter presenter);
}