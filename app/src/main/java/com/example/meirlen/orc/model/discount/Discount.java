package com.example.meirlen.orc.model.discount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Discount {

    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("product_category_id")
    @Expose
    private String productCategoryId;
    @SerializedName("product_producer_id")
    @Expose
    private Integer productProducerId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_description")
    @Expose
    private String productDescription;
    @SerializedName("product_price")
    @Expose
    private Integer productPrice;
    @SerializedName("product_discount_id")
    @Expose
    private Object productDiscountId;
    @SerializedName("sold_num")
    @Expose
    private String soldNum;
    @SerializedName("discount_id")
    @Expose
    private Object discountId;
    @SerializedName("discount_percent")
    @Expose
    private Object discountPercent;
    @SerializedName("discount_name")
    @Expose
    private Object discountName;
    @SerializedName("discount_type")
    @Expose
    private Integer discountType;
    @SerializedName("discount_image")
    @Expose
    private String discountImage;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Integer getProductProducerId() {
        return productProducerId;
    }

    public void setProductProducerId(Integer productProducerId) {
        this.productProducerId = productProducerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Object getProductDiscountId() {
        return productDiscountId;
    }

    public void setProductDiscountId(Object productDiscountId) {
        this.productDiscountId = productDiscountId;
    }

    public String getSoldNum() {
        return soldNum;
    }

    public void setSoldNum(String soldNum) {
        this.soldNum = soldNum;
    }

    public Object getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Object discountId) {
        this.discountId = discountId;
    }

    public Object getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Object discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Object getDiscountName() {
        return discountName;
    }

    public void setDiscountName(Object discountName) {
        this.discountName = discountName;
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

}