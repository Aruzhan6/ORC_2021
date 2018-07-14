package com.example.meirlen.orc.view;

import com.example.meirlen.orc.rest.model.Category;

import java.util.List;


public interface CategoryView extends LoadingView {

    void getCategories(List<Category> categories);


    void successLocalDb(String message);
}
