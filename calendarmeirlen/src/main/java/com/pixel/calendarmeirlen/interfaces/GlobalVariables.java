package com.pixel.calendarmeirlen.interfaces;

import android.graphics.Bitmap;

import com.pixel.calendarmeirlen.model.Booking;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class GlobalVariables {







    public static int oper_state = 0;
    public static boolean oper_state_minus = true;
    public static boolean min_hour_selectable = false;
    public  static  boolean firstItearation=true;
    public static DateTime start_date = null;
    public  static  boolean start_date_equals_current_date=false; //Если выбранная дата равна текущему дню
    public static DateTime end_date = null;
    public static String price = null;
    public static String start_time = null;
    public static IChangeHour iChangeHour = null;
    public static IShowTimePicker iShowTimePicker = null;

    public static DateTime start_date_booking = null;

    public static int type_calendar = 0;

    public static List<Booking> bookings=new ArrayList<>();

}