package com.pixel.calendarmeirlen.interfaces;

import org.joda.time.DateTime;

public interface IShowHours
{
    // Declaration of the template function for the interface
    public void showHourList(DateTime dateTime);
    public void showBookingList(DateTime startDateTime, DateTime endDateTime);


}