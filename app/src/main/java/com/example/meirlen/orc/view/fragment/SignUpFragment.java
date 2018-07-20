package com.example.meirlen.orc.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.model.Category;
import com.example.meirlen.orc.model.signup.ConfirmResponse;
import com.example.meirlen.orc.model.signup.SignupRequest;
import com.example.meirlen.orc.presenter.SignUpPresenter;
import com.example.meirlen.orc.view.SignUpView;
import com.example.meirlen.orc.view.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.sapereaude.maskedEditText.MaskedEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SignUpFragment extends Fragment implements SignUpView {

    @BindView(R.id.nameEditText)
    EditText nameEditText;

    @BindView(R.id.phoneEditText)
    MaskedEditText phoneEditText;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    SignUpPresenter presenter;


    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registration, container, false);
        ButterKnife.bind(this, rootView);
        App.getInstance().createSignUpComponent().inject(this);


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
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        ConfirmFragment fragment = ConfirmFragment.newInstance("7" + phoneEditText.getRawText());
        transaction.replace(R.id.containerView, fragment).addToBackStack(null).commit();
    }

    @Override
    public void response(boolean state, ConfirmResponse response) {

    }


    @OnClick(R.id.nextButton)
    public void onViewClicked() {

        if (phoneEditText.getRawText().length() <= 0)
            phoneEditText.setError(getString(R.string.requiredField));
        else
            presenter.signUp("7" + phoneEditText.getRawText(), nameEditText.getText().toString(), "2");

    }
}
