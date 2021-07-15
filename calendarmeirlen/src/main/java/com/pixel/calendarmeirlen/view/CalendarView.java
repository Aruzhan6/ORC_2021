package com.pixel.calendarmeirlen.view;

import android.support.annotation.LayoutRes;


public interface CalendarView extends BaseView {
    void setMonthLayoutResId(@LayoutRes int layoutResId);

    void setDayLayoutResId(@LayoutRes int layoutResId);
}
