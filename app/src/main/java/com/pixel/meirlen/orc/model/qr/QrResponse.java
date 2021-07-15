package com.pixel.meirlen.orc.model.qr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QrResponse {


    @SerializedName("data")
    @Expose
    private List<QR> qrs;

    public List<QR> getQrs() {
        return qrs;
    }

    public void setQrs(List<QR> qrs) {
        this.qrs = qrs;
    }
}