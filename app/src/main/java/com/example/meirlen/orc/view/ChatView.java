package com.example.meirlen.orc.view;

import com.example.meirlen.orc.rest.model.Category;

import java.util.List;



public interface ChatView extends LoadingView {

    void getChats(List<Category> messages);
}
