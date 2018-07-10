package com.example.meirlen.orc.model;

import com.example.meirlen.orc.api.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class ResponseGetChat extends BaseResponse {

@SerializedName("chats")
@Expose
private List<Chat> chats = null;

public List<Chat> getChats() {
return chats;
}

public void setChats(List<Chat> chats) {
this.chats = chats;
}

}