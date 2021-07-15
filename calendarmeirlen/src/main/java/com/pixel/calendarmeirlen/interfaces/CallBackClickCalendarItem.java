package com.pixel.calendarmeirlen.interfaces;

import com.pixel.calendarmeirlen.model.Booking;

import org.joda.time.DateTime;

public interface CallBackClickCalendarItem
{
    // Declaration of the template function for the interface

    public void BookingDayAction(Booking booking);
    public void bookingHoursAction(DateTime dateTime);
}