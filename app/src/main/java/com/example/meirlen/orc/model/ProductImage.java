package com.example.meirlen.orc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductImage implements Serializable {

    @SerializedName("image_id")
    @Expose
    private Integer imageId;
    @SerializedName("image_product_id")
    @Expose
    private String imageProductId;
    @SerializedName("image_path")
    @Expose
    private String imagePath;
    @SerializedName("image_alt")
    @Expose
    private String imageAlt;


    public ProductImage(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImageProductId() {
        return imageProductId;
    }

    public void setImageProductId(String imageProductId) {
        this.imageProductId = imageProductId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageAlt() {
        return imageAlt;
    }

    public void setImageAlt(String imageAlt) {
        this.imageAlt = imageAlt;
    }

}