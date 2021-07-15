package com.pixel.calendarmeirlen.presenter.interfeaces;

import com.pixel.calendarmeirlen.interactors.ViewInjector;
import com.pixel.calendarmeirlen.view.CustomizableCalendarView;

import java.util.List;


public interface CustomizableCalendarPresenter extends BasePresenter<CustomizableCalendarView>, ViewInjector {
    List<String> setupWeekDays();
}
