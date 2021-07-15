package com.pixel.meirlen.orc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DetailInfo implements Serializable {


    @SerializedName("similarProductsArray")
    @Expose
    private List<Product> similarProductsArray;


    @SerializedName("producersProductsArray")
    @Expose
    private List<Product> producersProductsArray;

    //

    public List<Product> getSimilarProductsArray() {
        return similarProductsArray;
    }


    public List<Product> getProducersProductsArray() {
        return producersProductsArray;
    }
}