package com.example.calendarmeirlen.model;

import org.joda.time.DateTime;

public class Booking {


    private DateTime timestampEnd;
    private DateTime timestampStart;
    private Integer bookId;
    private UserInfo userInfo;
    public Integer rent_type;


    public Booking(Integer rent_type, DateTime timestampStart, DateTime timestampEnd, Integer bookId, UserInfo userInfo) {
        this.rent_type = rent_type;
        this.timestampEnd = timestampEnd;
        this.timestampStart = timestampStart;
        this.bookId = bookId;
        this.userInfo = userInfo;
    }

    public Booking(Integer rent_type, DateTime timestampStart, DateTime timestampEnd, Integer bookId) {
        this.rent_type = rent_type;
        this.timestampEnd = timestampEnd;
        this.timestampStart = timestampStart;
        this.bookId = bookId;
    }

    public Booking(DateTime timestampStart, DateTime timestampEnd, Integer bookId) {
        this.timestampEnd = timestampEnd;
        this.timestampStart = timestampStart;
        this.bookId = bookId;
    }

    public DateTime getTimestampEnd() {
        return timestampEnd;
    }

    public DateTime getTimestampStart() {
        return timestampStart;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Integer getRent_type() {
        return rent_type;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
