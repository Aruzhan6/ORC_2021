package com.pixel.meirlen.orc.model.filter;

import com.pixel.meirlen.orc.model.Field;
import com.pixel.meirlen.orc.model.SearchValue;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FieldValues {

    @SerializedName("value")
    @Expose
    private SearchValue value = null;

    @SerializedName("field")
    @Expose
    private Field field = null;


    public FieldValues(SearchValue value, Field field) {
        this.value = value;
        this.field = field;
    }

    public SearchValue getValue() {
        return value;
    }

    public void setValue(SearchValue value) {
        this.value = value;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
