package com.example.meirlen.orc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Producer implements Serializable{

    @SerializedName("producer_id")
    @Expose
    private Integer producerId;
    @SerializedName("producer_user_id")
    @Expose
    private String producerUserId;
    @SerializedName("producer_city_id")
    @Expose
    private String producerCityId;
    @SerializedName("producer_name")
    @Expose
    private String producerName;
    @SerializedName("producer_address")
    @Expose
    private String producerAddress;
    @SerializedName("producer_descr")
    @Expose
    private String producerDescr;
    @SerializedName("producer_tel1")
    @Expose
    private String producerTel1;
    @SerializedName("producer_tel2")
    @Expose
    private String producerTel2;
    @SerializedName("producer_lat")
    @Expose
    private String producerLat;
    @SerializedName("producer_lng")
    @Expose
    private String producerLng;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    public String getProducerUserId() {
        return producerUserId;
    }

    public void setProducerUserId(String producerUserId) {
        this.producerUserId = producerUserId;
    }

    public String getProducerCityId() {
        return producerCityId;
    }

    public void setProducerCityId(String producerCityId) {
        this.producerCityId = producerCityId;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getProducerAddress() {
        return producerAddress;
    }

    public void setProducerAddress(String producerAddress) {
        this.producerAddress = producerAddress;
    }

    public String getProducerDescr() {
        return producerDescr;
    }

    public void setProducerDescr(String producerDescr) {
        this.producerDescr = producerDescr;
    }

    public String getProducerTel1() {
        return producerTel1;
    }

    public void setProducerTel1(String producerTel1) {
        this.producerTel1 = producerTel1;
    }

    public String getProducerTel2() {
        return producerTel2;
    }

    public void setProducerTel2(String producerTel2) {
        this.producerTel2 = producerTel2;
    }

    public String getProducerLat() {
        return producerLat;
    }

    public void setProducerLat(String producerLat) {
        this.producerLat = producerLat;
    }

    public String getProducerLng() {
        return producerLng;
    }

    public void setProducerLng(String producerLng) {
        this.producerLng = producerLng;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

}


