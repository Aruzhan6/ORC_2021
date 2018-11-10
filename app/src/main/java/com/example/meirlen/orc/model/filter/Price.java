package com.example.meirlen.orc.model.filter;

import com.example.meirlen.orc.model.filter.range.MaxValue;
import com.example.meirlen.orc.model.filter.range.MinValue;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("min")
    @Expose
    private int min;


    @SerializedName("max")
    @Expose
    private int max = 100000;


    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
