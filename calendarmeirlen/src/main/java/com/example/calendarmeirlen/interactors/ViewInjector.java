package com.example.calendarmeirlen.interactors;

import com.example.calendarmeirlen.view.CalendarView;
import com.example.calendarmeirlen.view.HeaderView;
import com.example.calendarmeirlen.view.SubView;
import com.example.calendarmeirlen.view.WeekDaysView;


public interface ViewInjector {
    void injectCalendarView(CalendarView calendarView);

    void injectHeaderView(HeaderView headerView);

    void injectSubView(SubView subView);

    void injectWeekDaysView(WeekDaysView weekDaysView);
}
