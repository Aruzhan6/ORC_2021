package com.pixel.calendarmeirlen.model;

public class VisitWorkTimeElement {
    private boolean is_day_passed = false;
    private String booking_id;

    private String time_of_day;
    private String start_date_of_day_body;
    private long startDateTime;
    private long endDateTime;
    private String start_date_of_day;
    private String end_date_of_day_body;
    private String end_date_of_day;
    private String infoDateAndTimeToVisit;
    private boolean rangeWorkTime = false;
    private boolean headerItem = false;
    private boolean lastTime = false;
    private boolean bodyItem = false;
    private boolean headerItemSelectable = false;
    private boolean bodyItemSelectable = false;
    private int type_price;
    private int duration;
    public String comment;
    public String name;
    public String avatar;
    public Integer price;

    public boolean isRangeWorkTime() {
        return rangeWorkTime;
    }

    public void setRangeWorkTime(boolean rangeWorkTime) {
        this.rangeWorkTime = rangeWorkTime;
    }


    public String getInfoDateAndTimeToVisit() {
        return infoDateAndTimeToVisit;
    }

    public void setInfoDateAndTimeToVisit(String infoDateAndTimeToVisit) {
        this.infoDateAndTimeToVisit = infoDateAndTimeToVisit;
    }

    public boolean isHeaderItem() {
        return headerItem;
    }

    public void setHeaderItem(boolean headerItem) {
        this.headerItem = headerItem;
    }

    public boolean isBodyItem() {
        return bodyItem;
    }

    public void setBodyItem(boolean bodyItem) {
        this.bodyItem = bodyItem;
    }

    private boolean is_open = true;

    public String getTime_of_day() {
        return time_of_day;
    }

    public void setTime_of_day(String time_of_day) {
        this.time_of_day = time_of_day;
    }

    public boolean is_open() {
        return is_open;
    }

    public void setIs_open(boolean is_open) {
        this.is_open = is_open;
    }

    public void setIs_day_passed(boolean is_day_passed) {
        this.is_day_passed = is_day_passed;
    }

    public boolean is_day_passed() {
        return is_day_passed;
    }

    public boolean isIs_day_passed() {
        return is_day_passed;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isIs_open() {
        return is_open;
    }

    public boolean isHeaderItemSelectable() {
        return headerItemSelectable;
    }

    public void setHeaderItemSelectable(boolean headerItemSelectable) {
        this.headerItemSelectable = headerItemSelectable;
    }

    public boolean isBodyItemSelectable() {
        return bodyItemSelectable;
    }

    public void setBodyItemSelectable(boolean bodyItemSelectable) {
        this.bodyItemSelectable = bodyItemSelectable;
    }

    public String getStart_date_of_day() {
        return start_date_of_day;
    }

    public void setStart_date_of_day(String start_date_of_day) {
        this.start_date_of_day = start_date_of_day;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }


    public boolean isLastTime() {
        return lastTime;
    }

    public void setLastTime(boolean lastTime) {
        this.lastTime = lastTime;
    }

    public String getEnd_date_of_day() {
        return end_date_of_day;
    }

    public void setEnd_date_of_day(String end_date_of_day) {
        this.end_date_of_day = end_date_of_day;
    }

    public String getEnd_date_of_day_body() {
        return end_date_of_day_body;
    }

    public void setEnd_date_of_day_body(String end_date_of_day_body) {
        this.end_date_of_day_body = end_date_of_day_body;
    }

    public String getStart_date_of_day_body() {
        return start_date_of_day_body;
    }

    public void setStart_date_of_day_body(String start_date_of_day_body) {
        this.start_date_of_day_body = start_date_of_day_body;
    }

    public int getType_price() {
        return type_price;
    }

    public void setType_price(int type_price) {
        this.type_price = type_price;
    }

    public long getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(long startDateTime) {
        this.startDateTime = startDateTime;
    }

    public long getEndDateTime() {
        return endDateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setEndDateTime(long endDateTime) {
        this.endDateTime = endDateTime;
    }
}