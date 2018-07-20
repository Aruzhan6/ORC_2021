package com.example.meirlen.orc.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.MainActivity;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.SessionManager;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.signup.ConfirmResponse;
import com.example.meirlen.orc.presenter.SignUpPresenter;
import com.example.meirlen.orc.view.SignUpView;
import com.example.meirlen.orc.view.activity.ChildCategoryActivity;
import com.example.meirlen.orc.view.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.sapereaude.maskedEditText.MaskedEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ConfirmFragment extends Fragment implements SignUpView {


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    SignUpPresenter presenter;


    @Inject
    SessionManager sessionManager;

    @BindView(R.id.codeET)
    EditText codeET;

    private String number;
    private static final String TAG = "ConfirmFragment";

    public static final String EXTRA_NUMBER = "extra:number";

    public static ConfirmFragment newInstance() {
        return new ConfirmFragment();
    }

    public static ConfirmFragment newInstance(String param1) {
        ConfirmFragment fragment = new ConfirmFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_NUMBER, param1);
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
        View rootView = inflater.inflate(R.layout.fragment_confirmation, container, false);
        ButterKnife.bind(this, rootView);
        App.getInstance().createSignUpComponent().inject(this);
        assert getArguments() != null;
        number = getArguments().getString(EXTRA_NUMBER);

        presenter.setView(this);

        return rootView;
    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
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


    @Override
    public void response(boolean state) {
    }

    @Override
    public void response(boolean state, ConfirmResponse response) {

        Log.d(TAG, "response: "+response.getToken());
        sessionManager.setAccessToken(response.getToken());
        sessionManager.setUserId(String.valueOf(response.getId()));
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.sendButton)
    public void onSendCode() {
        if (codeET.getText().length() == 4) {
            String code = codeET.getText().toString();
            presenter.confirm(code, number);

        } else {
            Toast.makeText(getContext(), "XXXX", Toast.LENGTH_SHORT).show();
        }
    }
}
