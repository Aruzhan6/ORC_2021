package com.example.meirlen.orc.model.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfirmRequest {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("sms")
    @Expose
    private String sms;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
}