package com.example.calendarmeirlen.model;

import org.joda.time.DateTime;


public class CalendarItem {
    private Booking booking;
    private long id;
    private DateTime dateTime;
    private boolean selected;
    private boolean month_booking_day;
    private boolean month_booking_hourly;


    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }


    public CalendarItem(int day, int month, int year) {
        this.dateTime = new DateTime(year, month, day, 0, 0);
        this.id = dateTime.getMillis();
    }

    public DateTime getDateTime() {
        return dateTime;
    }


    public boolean isSelectable() {
        return selected;
    }

    public void setSelectable(boolean selected) {
        this.selected = selected;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int compareTo(DateTime today) {
        return dateTime.compareTo(today);
    }

    public String getDayString() {
        return getDay() + "";
    }

    public int getDay() {
        return dateTime.getDayOfMonth();
    }

    public int getMonth() {
        return dateTime.getMonthOfYear();
    }

    public int getYear() {
        return dateTime.getYear();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isMonth_booking_day() {
        return month_booking_day;
    }

    public void setMonth_booking_day(boolean month_booking_day) {
        this.month_booking_day = month_booking_day;
    }

    public boolean isMonth_booking_hourly() {
        return month_booking_hourly;
    }

    public void setMonth_booking_hourly(boolean month_booking_hourly) {
        this.month_booking_hourly = month_booking_hourly;
    }



}
