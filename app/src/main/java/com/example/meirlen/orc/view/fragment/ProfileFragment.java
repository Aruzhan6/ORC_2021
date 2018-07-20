package com.example.meirlen.orc.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.SessionManager;
import com.example.meirlen.orc.model.signup.ConfirmResponse;
import com.example.meirlen.orc.presenter.SignUpPresenter;
import com.example.meirlen.orc.view.SignUpView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileFragment extends Fragment implements SignUpView {

    private static final String TAG = "ProfileFragment";

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    SignUpPresenter presenter;


    @Inject
    SessionManager sessionManager;


    @BindView(R.id.textViewUserName)
    TextView textViewUserName;

    @BindView(R.id.textViewPhone)
    TextView textViewPhone;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    public static ProfileFragment newInstance(String param1) {
        ProfileFragment fragment = new ProfileFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, rootView);
        App.getInstance().createSignUpComponent().inject(this);
        assert getArguments() != null;
        presenter.setView(this);

        Log.d(TAG, "onCreateView: " + sessionManager.getAccessToken());
        presenter.getProfile(sessionManager.getAccessToken());
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
        textViewPhone.setText(response.getPhone());
        textViewUserName.setText(response.getName());

    }


}
