package com.example.calendarmeirlen.interactors;

import android.view.View;
import android.view.ViewGroup;

import com.example.calendarmeirlen.adapter.WeekDaysViewAdapter;
import com.example.calendarmeirlen.model.CalendarItem;

import org.joda.time.DateTime;

import java.util.List;


public interface ViewInteractor {
    void onCustomizableCalendarBindView(View view);

    void onHeaderBindView(ViewGroup view);

    void onWeekDaysBindView(View view);

    void onWeekDayBindView(WeekDaysViewAdapter.WeekDayVH holder, String weekDay);

    void onSubViewBindView(View view, String month);

    void onCalendarBindView(View view);

    void onMonthBindView(View view);

    View onMonthCellBindView(View view, CalendarItem currentItem);

    boolean hasImplementedDayCalculation();

    List<CalendarItem> calculateDays(int year, int month, int firstDayOfMonth, int lastDayOfMonth);

    boolean hasImplementedSelection();

    int setSelected(boolean multipleSelection, DateTime dateSelected);

    boolean hasImplementedMonthCellBinding();

    View getMonthGridView(View rootView);

    boolean hasImplementedWeekDayNameFormat();

    String formatWeekDayName(String nameOfDay);
}
