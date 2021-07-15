package com.pixel.meirlen.orc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailResponse {


    @SerializedName("product")
    @Expose
    private DetailInfo product;


    public DetailInfo getProduct() {
        return product;
    }

    public void setProduct(DetailInfo product) {
        this.product = product;
    }
}