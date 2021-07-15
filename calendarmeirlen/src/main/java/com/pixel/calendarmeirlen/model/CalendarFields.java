package com.pixel.calendarmeirlen.model;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.pixel.calendarmeirlen.model.CalendarFields.CURRENT_MONTH;
import static com.pixel.calendarmeirlen.model.CalendarFields.FIRST_DAY_OF_WEEK;
import static com.pixel.calendarmeirlen.model.CalendarFields.FIRST_SELECTED_DAY;
import static com.pixel.calendarmeirlen.model.CalendarFields.LAST_SELECTED_DAY;
import static com.pixel.calendarmeirlen.model.CalendarFields.MONTHS;
import static com.pixel.calendarmeirlen.model.CalendarFields.MULTIPLE_SELECTION;


@StringDef({
        FIRST_SELECTED_DAY,
        LAST_SELECTED_DAY,
        CURRENT_MONTH,
        MONTHS,
        MULTIPLE_SELECTION,
        FIRST_DAY_OF_WEEK
})

@Retention(RetentionPolicy.SOURCE)
public @interface CalendarFields {
    String ACTIVE_DAY = "activeDay";
    String FIRST_SELECTED_DAY = "firstSelectedDay";
    String LAST_SELECTED_DAY = "lastSelectedDay";
    String CURRENT_MONTH = "currentMonth";
    String MONTHS = "months";
    String MULTIPLE_SELECTION = "multipleSelection";
    String FIRST_DAY_OF_WEEK = "firstDayOfWeek";
}