package com.example.calendarmeirlen.presenter.interfeaces;

import com.example.calendarmeirlen.interactors.ViewInjector;
import com.example.calendarmeirlen.view.CustomizableCalendarView;

import java.util.List;


public interface CustomizableCalendarPresenter extends BasePresenter<CustomizableCalendarView>, ViewInjector {
    List<String> setupWeekDays();
}
