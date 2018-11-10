package com.example.meirlen.orc.model.qr;

import com.example.meirlen.orc.model.Producer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QR {

    @SerializedName("producer")
    @Expose
    private Producer producer;


    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}