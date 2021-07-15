package com.pixel.calendarmeirlen.interactors;

import com.pixel.calendarmeirlen.view.CalendarView;
import com.pixel.calendarmeirlen.view.HeaderView;
import com.pixel.calendarmeirlen.view.SubView;
import com.pixel.calendarmeirlen.view.WeekDaysView;


public interface ViewInjector {
    void injectCalendarView(CalendarView calendarView);

    void injectHeaderView(HeaderView headerView);

    void injectSubView(SubView subView);

    void injectWeekDaysView(WeekDaysView weekDaysView);
}
