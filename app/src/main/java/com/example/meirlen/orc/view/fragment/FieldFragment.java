package com.example.meirlen.orc.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.interfaces.OnFieldItemSelectedListener;
import com.example.meirlen.orc.interfaces.OnValueClearIListener;
import com.example.meirlen.orc.model.Field;
import com.example.meirlen.orc.model.SearchValue;
import com.example.meirlen.orc.presenter.FieldPresenter;
import com.example.meirlen.orc.view.FieldView;
import com.example.meirlen.orc.view.activity.ProductListActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class FieldFragment extends Fragment implements FieldView, OnValueClearIListener {


    @Inject
    FieldPresenter presenter;


    List<Field> list = new ArrayList<>();


    @BindView(R.id.mainFieldLayoutSearch)
    LinearLayout mainFieldLayout;
    @BindView(R.id.acceptButton)
    Button acceptButton;

    private String fieldType;
    private String fieldName;

    Activity activity;
    private Unbinder unbinder;


    private boolean isCategoryField;

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
        unbinder = ButterKnife.bind(this, rootView);
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
        acceptButton.setText(R.string.show_all_products);
        isCategoryField = true;

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
        acceptButton.setText(R.string.apply_filter);
        isCategoryField = false;

        for (SearchValue searchValue : searchFields) {
            View child = null;
            TextView textViewTitle;
            switch (fieldType) {
                case "MULTIPLE_SELECT":
                    child = getLayoutInflater().inflate(R.layout.item_checkbox, null);
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
            textViewTitle.setText(searchValue.getValue());
        }

    }


    @Override
    public void onValueClear() {
        init();
    }


    @OnClick(R.id.acceptButton)
    public void onViewClicked() {
        if (isCategoryField) {
            Intent intent = new Intent(getContext(), ProductListActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_righ);
        } else {
            init();

        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
