package com.example.meirlen.orc.model.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupRequest {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("name")
    @Expose
    private String name;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}