package com.example.calendarmeirlen.interactors;

import android.support.annotation.LayoutRes;


public interface LayoutInteractor {
    void setCustomizableCalendarLayoutResId(@LayoutRes int resId);

    void setHeaderLayoutResId(@LayoutRes int resId);

    void setWeekDayResId(@LayoutRes int resId);

    void setSubViewResId(@LayoutRes int resId);

    void setMonthResId(@LayoutRes int resId);

    void setDayResId(@LayoutRes int resId);
}
