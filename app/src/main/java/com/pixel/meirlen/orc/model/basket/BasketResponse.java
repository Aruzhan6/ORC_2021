package com.pixel.meirlen.orc.model.basket;

import com.pixel.meirlen.orc.model.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BasketResponse {

    @SerializedName("list")
    @Expose
    private List<Product> list = null;


    @SerializedName("total")
    @Expose
    private Double total;


    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}