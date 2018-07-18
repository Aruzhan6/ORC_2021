package com.example.meirlen.orc.view;

import com.example.meirlen.orc.model.Field;
import com.example.meirlen.orc.model.Product;
import com.example.meirlen.orc.model.SearchValue;

import java.util.List;


public interface FieldView extends LoadingView {

    void getList(List<Field> fields);
    void getFieldValues(List<SearchValue> fields);

}
