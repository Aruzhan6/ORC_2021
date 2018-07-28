package com.example.meirlen.orc.view;

import com.example.meirlen.orc.model.Category;

import java.util.List;


public interface CategoryView extends LoadingView {

    void getCategories(List<Category> categories);
    void getCardCount(String count);
    void successLocalDb(String message);
}
