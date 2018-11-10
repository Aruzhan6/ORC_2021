package com.example.meirlen.orc.model.discount;

import com.example.meirlen.orc.model.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Discount {
    @SerializedName("discount_id")
    @Expose
    private Integer discountId;
    @SerializedName("discount_name")
    @Expose
    private String discountName;
    @SerializedName("discount_percent")
    @Expose
    private Integer discountPercent;
    @SerializedName("discount_type")
    @Expose
    private Integer discountType;
    @SerializedName("discount_image")
    @Expose
    private String discountImage;
    @SerializedName("discount_producer_id")
    @Expose
    private Object discountProducerId;
    @SerializedName("discount_image_desktop")
    @Expose
    private String discountImageDesktop;
    @SerializedName("orderIndex")
    @Expose
    private String orderIndex;

    @SerializedName("randomProducts")
    @Expose
    private List<Product> randomProducts;


    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Integer getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }

    public String getDiscountImage() {
        return discountImage;
    }

    public void setDiscountImage(String discountImage) {
        this.discountImage = discountImage;
    }

    public Object getDiscountProducerId() {
        return discountProducerId;
    }

    public void setDiscountProducerId(Object discountProducerId) {
        this.discountProducerId = discountProducerId;
    }

    public String getDiscountImageDesktop() {
        return discountImageDesktop;
    }

    public void setDiscountImageDesktop(String discountImageDesktop) {
        this.discountImageDesktop = discountImageDesktop;
    }

    public String getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(String orderIndex) {
        this.orderIndex = orderIndex;
    }


    public List<Product> getRandom_products() {
        return randomProducts;
    }

    public void setRandom_products(List<Product> randomProducts) {
        this.randomProducts = randomProducts;
    }
}