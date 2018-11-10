package com.example.calendarmeirlen.view;


import org.joda.time.DateTime;


public interface MonthView extends BaseView {
    void setSelected(DateTime dateSelected);

    void refreshDays();

    void unsubscribe();
}
