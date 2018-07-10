package com.example.meirlen.orc.view;

import com.example.meirlen.orc.model.Chat;

import java.util.List;



public interface ChatView extends LoadingView {

    void getChats(List<Chat> messages);
}
