package com.example.meirlen.orc.presenter;


import com.example.meirlen.orc.view.ChatView;

public interface ChatPresenter extends BasePresenter<ChatView> {

    void getMessages(String token);

}
