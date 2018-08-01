package com.example.meirlen.orc.model.request;

import com.example.meirlen.orc.model.filter.BooleanType;
import com.example.meirlen.orc.model.filter.MultipleSelect;
import com.example.meirlen.orc.model.filter.RangeInt;
import com.example.meirlen.orc.model.filter.StringType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filter {

    @SerializedName("multipleSelect")
    @Expose
    private MultipleSelect multipleSelect;
    @SerializedName("rangeInt")
    @Expose
    private RangeInt rangeInt;
    @SerializedName("boolean")
    @Expose
    private BooleanType _boolean;
    @SerializedName("string")
    @Expose
    private StringType string;


    @SerializedName("name")
    @Expose
    private String name;


    public MultipleSelect getMultipleSelect() {
        return multipleSelect;
    }

    public void setMultipleSelect(MultipleSelect multipleSelect) {
        this.multipleSelect = multipleSelect;
    }

    public RangeInt getRangeInt() {
        return rangeInt;
    }

    public void setRangeInt(RangeInt rangeInt) {
        this.rangeInt = rangeInt;
    }


    public BooleanType get_boolean() {
        return _boolean;
    }

    public void set_boolean(BooleanType _boolean) {
        this._boolean = _boolean;
    }

    public StringType getString() {
        return string;
    }

    public void setString(StringType string) {
        this.string = string;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
