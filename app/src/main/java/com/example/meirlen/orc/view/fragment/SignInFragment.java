package com.example.meirlen.orc.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.model.signup.ConfirmResponse;
import com.example.meirlen.orc.presenter.SignUpPresenter;
import com.example.meirlen.orc.view.SignUpView;
import com.onesignal.OneSignal;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser;
import ru.tinkoff.decoro.slots.Slot;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;


public class SignInFragment extends Fragment implements SignUpView, TextWatcher {

    @BindView(R.id.phoneEditText)
    EditText phoneEditText;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    SignUpPresenter presenter;
    @BindView(R.id.login_btn)
    Button loginBtn;

    private String API_KEY;

    @BindView(R.id.main_layout)
    RelativeLayout mainLayout;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, rootView);
        formatWatcher(phoneEditText);
   
        App.getInstance().createSignUpComponent().inject(this);
        loginBtn.setEnabled(false);
        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                Log.d("userId", "User:" + userId);
                API_KEY = userId;
                //  update_player_id_push(userId);
                if (registrationId != null)
                    Log.d("registrationId", "registrationId:" + registrationId);
            }
        });
        presenter.setView(this);
        phoneEditText.addTextChangedListener(this);
        phoneEditText.setOnFocusChangeListener((view, focused) -> {
            InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (focused) {
                assert keyboard != null;
                keyboard.showSoftInput(phoneEditText, 0);
            }
            else {
                assert keyboard != null;
                keyboard.hideSoftInputFromWindow(phoneEditText.getWindowToken(), 0);
            }
        });

        phoneEditText.requestFocus();
        return rootView;
    }


    @Override
    public void showLoading() {
        mainLayout.setAlpha((float) 0.7);
        progressBar.setVisibility(View.VISIBLE);
        Objects.requireNonNull(getActivity()).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void hideLoading() {
        mainLayout.setAlpha((float) 1.0);
        progressBar.setVisibility(View.GONE);
        Objects.requireNonNull(getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

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
        SmSFragment fragment = SmSFragment.newInstance(getNumber(phoneEditText.getText().toString()), API_KEY);
        transaction.replace(R.id.containerView, fragment).addToBackStack(null).commit();
    }

    @Override
    public void response(boolean state, ConfirmResponse response) {

    }


    @OnClick(R.id.login_btn)
    public void onViewClicked() {
        presenter.signIn(getNumber(phoneEditText.getText().toString()), API_KEY);
    }


    @Override
    public void afterTextChanged(Editable s) {
        if (phoneEditText.getText().length() > 15) {
            loginBtn.setEnabled(true);
        } else {
            loginBtn.setEnabled(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    public static void formatWatcher(EditText editText) {
        Slot[] slots = new UnderscoreDigitSlotsParser().parseSlots("8-(___)-___-____");
        FormatWatcher formatWatcher = new MaskFormatWatcher( // форматировать текст будет вот он
                MaskImpl.createTerminated(slots)
        );


        formatWatcher.installOn(editText);

    }

    public static String getNumber(String number) {


        String originalString1 = number.replaceAll("-", "");
        String originalString2 = originalString1.replaceAll("\\(", "");
        number = originalString2.replaceAll("\\)", "");
        if (number.startsWith("8")) {
            number = number.startsWith("8") ? number.substring(1) : number;
            number = "7" + number;

        }

        return number;
    }


}
