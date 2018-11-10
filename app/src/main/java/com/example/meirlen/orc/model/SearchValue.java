package com.example.meirlen.orc.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity
public class SearchValue {


    @PrimaryKey
    @SerializedName("value_id")
    @Expose
    private Integer valueId;
    @SerializedName("value_field_id")
    @Expose
    private String valueFieldId;
    @SerializedName("onlythis")
    @Expose
    private String onlythis;
    @SerializedName("value")
    @Expose
    private String value;

    private boolean selectable=false;

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public String getValueFieldId() {
        return valueFieldId;
    }

    public void setValueFieldId(String valueFieldId) {
        this.valueFieldId = valueFieldId;
    }

    public String getOnlythis() {
        return onlythis;
    }

    public void setOnlythis(String onlythis) {
        this.onlythis = onlythis;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }
}