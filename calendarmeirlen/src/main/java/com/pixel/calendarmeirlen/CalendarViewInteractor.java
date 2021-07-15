package com.pixel.calendarmeirlen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pixel.calendarmeirlen.adapter.WeekDaysViewAdapter;
import com.pixel.calendarmeirlen.interactors.ViewInteractor;
import com.pixel.calendarmeirlen.interfaces.GlobalVariables;
import com.pixel.calendarmeirlen.interfaces.IChangeHour;
import com.pixel.calendarmeirlen.model.CalendarItem;
import com.pixel.calendarmeirlen.model.MyCalendar;

import org.joda.time.DateTime;

import java.util.List;


public class CalendarViewInteractor implements ViewInteractor, IChangeHour {
    private Context context;
    private MyCalendar calendar;

    DateTime firstDate;
    DateTime lastDate;

    public CalendarViewInteractor(Context context) {
        this.context = context;
        GlobalVariables.iChangeHour = this;
    }

    @Override
    public void onCustomizableCalendarBindView(View view) {

    }

    @Override
    public void onHeaderBindView(ViewGroup view) {
        RelativeLayout layout;

        layout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.calendar_header, view);


        updateCalendar(calendar);
    }

    @Override
    public void onWeekDaysBindView(View view) {

    }

    @Override
    public void onWeekDayBindView(WeekDaysViewAdapter.WeekDayVH holder, String weekDay) {

    }

    @Override
    public void onSubViewBindView(View view, String month) {

    }

    @Override
    public void onCalendarBindView(View view) {

    }

    @Override
    public void onMonthBindView(View view) {

    }

    @Override
    public View onMonthCellBindView(View view, CalendarItem currentItem) {
        return null;
    }

    @Override
    public boolean hasImplementedDayCalculation() {
        return false;
    }

    @Override
    public List<CalendarItem> calculateDays(int year, int month, int firstDayOfMonth, int lastDayOfMonth) {
        return null;
    }

    @Override
    public boolean hasImplementedSelection() {
        return false;
    }

    @Override
    public int setSelected(boolean multipleSelection, DateTime dateSelected) {
        return -1;
    }

    @Override
    public boolean hasImplementedMonthCellBinding() {
        return false;
    }

    @Override
    public View getMonthGridView(View rootView) {
        return null;
    }

    @Override
    public boolean hasImplementedWeekDayNameFormat() {
        return false;
    }

    @Override
    public String formatWeekDayName(String nameOfDay) {
        return null;
    }

    public void updateCalendar(MyCalendar calendar) {
        this.calendar = calendar;

        updDateTime(null, null);


    }


    @Override
    public void changeStartTime(String hour) {


        updDateTime(hour, null);

    }

    @Override
    public void changeEndTime(String hour) {
        updDateTime(null, hour);
    }

    private void updDateTime(String startTime, String endTime) {




    }

    public boolean compareTwoDate(DateTime compareDateTime) {
        DateTime currentDateTime = new DateTime();
        if (currentDateTime.getDayOfMonth() == compareDateTime.getDayOfMonth() & currentDateTime.getMonthOfYear() == compareDateTime.getMonthOfYear())
            return true;

        return false;
    }
}
