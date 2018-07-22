package com.example.meirlen.orc.model.basket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BasketResponse {

@SerializedName("list")
@Expose
private List<Basket> list = null;

    public List<Basket> getList() {
        return list;
    }

    public void setList(List<Basket> list) {
        this.list = list;
    }
}