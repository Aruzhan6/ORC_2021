package com.pixel.meirlen.orc.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pixel.meirlen.orc.App;
import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.helper.FilterFactory;
import com.pixel.meirlen.orc.helper.SessionManager;
import com.pixel.meirlen.orc.interfaces.OnFieldItemSelectedListener;
import com.pixel.meirlen.orc.interfaces.OnValueClearIListener;
import com.pixel.meirlen.orc.model.Field;
import com.pixel.meirlen.orc.model.SearchValue;
import com.pixel.meirlen.orc.model.filter.Price;
import com.pixel.meirlen.orc.model.request.Filter;
import com.pixel.meirlen.orc.presenter.FieldPresenter;
import com.pixel.meirlen.orc.view.FieldView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.pixel.meirlen.orc.view.activity.ProductListActivity.EXTRA_FILTER;
import static com.pixel.meirlen.orc.view.activity.ProductListActivity.EXTRA_ID_CATEGORY;


public class FieldFragment extends Fragment implements FieldView, OnValueClearIListener {


    private static final String TAG = "FieldFragment";

    @Inject
    FieldPresenter presenter;

    @Inject
    Gson gson;

    List<Field> list = new ArrayList<>();


    @BindView(R.id.acceptButton)
    Button acceptButton;


    @BindView(R.id.includeWordsEditText)
    EditText edDescWord;
    @BindView(R.id.fromEditText)
    EditText fromEditText;
    @BindView(R.id.toEditText)
    EditText toEditText;

    private String fieldType;
    private String fieldName;

    Activity activity;
    private Unbinder unbinder;


    @BindView(R.id.mainFieldLayoutSearch)
    LinearLayout mainFieldLayout;

    @Inject
    SessionManager sessionManager;

    private boolean isCategoryField = true;

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
        initLocalDate();
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
        presenter.getFields(Objects.requireNonNull(getActivity()).getIntent().getStringExtra(EXTRA_ID_CATEGORY));
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

        mainFieldLayout.removeAllViews();
        acceptButton.setText(R.string.apply_filter);
        isCategoryField = false;

        for (SearchValue searchValue : searchFields) {
            View child = null;
            TextView textViewTitle;
            switch (fieldType) {
                case "MULTIPLE_SELECT":
                    child = getLayoutInflater().inflate(R.layout.item_checkbox, null);

                    CheckBox checkBox = child.findViewById(R.id.checkBox);
                    checkBox.setChecked(searchValue.isSelectable());


                    checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        if (isChecked)
                            searchValue.setSelectable(true);

                        else {
                            searchValue.setSelectable(false);
                        }
                        presenter.updateSearchValue(searchValue);

                    });
                    //


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

    private void initLocalDate() {
        fromEditText.setText(sessionManager.getFrom());
        toEditText.setText(sessionManager.getTo());
        edDescWord.setText(sessionManager.getKeyWord());
    }

    @Override
    public void onValueClear() {
        init();
    }


    @OnClick(R.id.acceptButton)
    public void onViewClicked() {
        int from;
        int to;
        if (isCategoryField) {
            Filter filter = FilterFactory.createFilter();
            Price price = new Price();
            if (!fromEditText.getText().toString().equals("")) {
                from = Integer.parseInt(fromEditText.getText().toString());
                sessionManager.setFrom(String.valueOf(from));
                price.setMin(from);
            }
            if (!toEditText.getText().toString().equals("")) {
                to = Integer.parseInt(toEditText.getText().toString());
                sessionManager.setTo(String.valueOf(to));
                price.setMax(to);
            }
            filter.setPrice(price);
            Intent returnIntent = new Intent();
            sessionManager.setKeyWord(edDescWord.getText().toString());
            filter.setDescription(edDescWord.getText().toString());
            String myJson = gson.toJson(filter);
            returnIntent.putExtra(EXTRA_FILTER, myJson);
            getActivity().setResult(Activity.RESULT_OK, returnIntent);
            getActivity().finish();
        } else {
            init();

        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: Presenter has killed");
        unbinder.unbind();
        presenter.destroy();
    }
}
