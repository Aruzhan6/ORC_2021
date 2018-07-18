package com.example.meirlen.orc.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.Constans;
import com.example.meirlen.orc.interfaces.OnFieldItemSelectedListener;
import com.example.meirlen.orc.interfaces.OnValueClearIListener;
import com.example.meirlen.orc.model.Field;

import com.example.meirlen.orc.model.SearchValue;
import com.example.meirlen.orc.presenter.FieldPresenter;
import com.example.meirlen.orc.view.FieldView;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FieldFragment extends Fragment implements FieldView, OnValueClearIListener {


    @Inject
    FieldPresenter presenter;


    List<Field> list = new ArrayList<>();


    @BindView(R.id.mainFieldLayoutSearch)
    LinearLayout mainFieldLayout;

    private String fieldType;
    private String fieldName;

    Activity activity;

    public static FieldFragment newInstance() {
        return new FieldFragment();
    }

    public static FieldFragment newInstance(String param1, String param2) {
        FieldFragment fragment = new FieldFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_filter, container, false);
        ButterKnife.bind(this, rootView);
        App.getInstance().createFieldComponent().inject(this);
        presenter.setView(this);
        init();

        return rootView;
    }


    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loadingFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void init() {
        presenter.getFields("5");
    }


    @Override
    public void getList(List<Field> fields) {
        list.clear();
        list.addAll(fields);
        drawCategoryFields(fields);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
    }

    @Override
    public void getFieldValues(List<SearchValue> fields) {
        if (fields.size() > 0) {
            drawFieldsValue(fields);
            ((OnFieldItemSelectedListener) activity).onFieldItemPicked(fieldName);
        } else {
            Toast.makeText(activity, "Not Values", Toast.LENGTH_SHORT).show();
        }


    }

    public void drawCategoryFields(List<Field> searchFields) {
        mainFieldLayout.removeAllViews();
        for (Field searchField : searchFields) {
            View child = null;
            TextView textViewTitle = null;
            switch (searchField.getFieldType()) {
                case "MULTIPLE_SELECT":
                    child = getLayoutInflater().inflate(R.layout.item_header_field, null);
                    child.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            presenter.getFieldValues(String.valueOf(searchField.getFieldId()));
                            fieldType = searchField.getFieldType();
                            fieldName = searchField.getFieldName();
                            mainFieldLayout.removeAllViews();
                        }
                    });
                    break;
                case "STRING":
                    child = getLayoutInflater().inflate(R.layout.item_string_field, null);
                    break;
                case "BOOLEAN":
                    child = getLayoutInflater().inflate(R.layout.item_boolean_field, null);
                    break;
                case "RANGE_INT":
                    child = getLayoutInflater().inflate(R.layout.item_range_field, null);
                    break;


            }
            mainFieldLayout.addView(child);
            textViewTitle = child.findViewById(R.id.headerTitle);
            textViewTitle.setText(searchField.getFieldName());


        }

    }

    public void drawFieldsValue(List<SearchValue> searchFields) {
        for (SearchValue searchValue : searchFields) {
            View child;
            TextView textViewTitle;
            switch (fieldType) {
                case "MULTIPLE_SELECT":
                    child = getLayoutInflater().inflate(R.layout.item_checkbox, null);
                    mainFieldLayout.addView(child);
                    break;
                case "STRING":
                    child = getLayoutInflater().inflate(R.layout.item_string_field, null);
                    mainFieldLayout.addView(child);
                    break;
                case "BOOLEAN":
                    child = getLayoutInflater().inflate(R.layout.item_boolean_field, null);
                    Toast.makeText(getContext(), "213", Toast.LENGTH_SHORT).show();
                    mainFieldLayout.addView(child);
                    break;
                case "RANGE_INT":
                    child = getLayoutInflater().inflate(R.layout.item_range_field, null);
                    mainFieldLayout.addView(child);
                    break;
            }


        }

    }


    @Override
    public void onValueClear() {
        init();
    }
}
