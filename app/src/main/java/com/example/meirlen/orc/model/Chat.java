package com.example.meirlen.orc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Chat {

    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("new_messages")
    @Expose
    private Integer newMessages;

    @SerializedName("info")
    @Expose
    private String info;



    //"chat_id"
    @SerializedName("chat_id")
    @Expose
    private String chat_id;


    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getNewMessages() {
        return newMessages;
    }

    public void setNewMessages(Integer newMessages) {
        this.newMessages = newMessages;
    }



    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

}