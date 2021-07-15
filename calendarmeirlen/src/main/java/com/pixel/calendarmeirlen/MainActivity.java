package com.pixel.calendarmeirlen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pixel.calendarmeirlen.components.CustomizableCalendar;
import com.pixel.calendarmeirlen.interactors.AUCalendar;
import com.pixel.calendarmeirlen.model.MyCalendar;

import org.joda.time.DateTime;

import java.util.Calendar;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    CustomizableCalendar customizableCalendar;

    private CompositeDisposable subscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customizableCalendar=(CustomizableCalendar)findViewById(R.id.view_month);

        updateData();
    }

    private void updateData() {


        subscriptions = new CompositeDisposable();
        // setting up first and last month that must be showed in the calendar
         Calendar cal = Calendar.getInstance();
          int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        DateTime firstMonth = new DateTime().withDayOfMonth(1).plusDays(dayOfMonth);
        DateTime lastMonth = new DateTime().plusMonths(3).withDayOfMonth(1);

        // create the Calendar obj and setting it up with some configs like:
        // - first selected day
        // - last selected day
        // - multiple selection
        final MyCalendar calendar = new MyCalendar(firstMonth, lastMonth);
      //  calendar.setFirstSelectedDay(new DateTime().plusDays(4));
       // calendar.setLastSelectedDay(new DateTime().plusDays(6));
        calendar.setMultipleSelection(true);

        // create a CalendarViewInteractor obj needed to interact with the CustomizableCalendar
        final CalendarViewInteractor calendarViewInteractor = new CalendarViewInteractor(getBaseContext());

        // create the AUCalendar obj and observes changes on it
        AUCalendar auCalendar = AUCalendar.getInstance(calendar);
        calendarViewInteractor.updateCalendar(calendar);
        subscriptions.add(
                auCalendar.observeChangesOnCalendar()
                        .subscribe(changeSet -> calendarViewInteractor.updateCalendar(calendar))
        );

        // inject (set) the calendarViewInteractor to the CustomizableCalendar
        customizableCalendar.injectViewInteractor(calendarViewInteractor);
    }


}