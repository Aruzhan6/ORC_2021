package com.pixel.meirlen.orc.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pixel.meirlen.orc.App;
import com.pixel.meirlen.orc.MainActivity;
import com.pixel.meirlen.orc.R;
import com.pixel.meirlen.orc.helper.SessionManager;
import com.pixel.meirlen.orc.model.signup.ConfirmResponse;
import com.pixel.meirlen.orc.presenter.SignUpPresenter;
import com.pixel.meirlen.orc.view.SignUpView;
import com.jkb.vcedittext.VerificationAction;
import com.jkb.vcedittext.VerificationCodeEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SmSFragment extends Fragment implements SignUpView {


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    SignUpPresenter presenter;


    @Inject
    SessionManager sessionManager;
    @BindView(R.id.sendButton)
    Button sendButton;
    @BindView(R.id.infoTV)
    TextView infoTV;


    private String number;
    private String API_KEY;
    private static final String TAG = "SmSFragment";

    public static final String EXTRA_NUMBER = "extra:number";
    public static final String EXTRA_API_KEY = "extra:api_key";


    @BindView(R.id.textView21)
    TextView mTimer;
    @BindView(R.id.textView20)
    TextView info;

    @BindView(R.id.am_et)
    VerificationCodeEditText verificationCodeEditText;

    public static SmSFragment newInstance() {
        return new SmSFragment();
    }

    public static SmSFragment newInstance(String param1, String param2) {
        SmSFragment fragment = new SmSFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_NUMBER, param1);
        args.putString(EXTRA_API_KEY, param2);
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
        sendButton.setEnabled(false);
        App.getInstance().createSignUpComponent().inject(this);
        assert getArguments() != null;
        number = getArguments().getString(EXTRA_NUMBER);
        API_KEY = getArguments().getString(EXTRA_API_KEY);
        infoTV.append(" +"+number);
        presenter.setView(this);
        startDown();

        verificationCodeEditText.setOnVerificationCodeChangedListener(new VerificationCodeEditText
                .OnVerificationCodeChangedListener() {

            @Override
            public void onVerCodeChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 3) {
                    sendButton.setEnabled(true);
                } else {
                    sendButton.setEnabled(false);
                }
            }

            @Override
            public void onInputCompleted(CharSequence s) {
                presenter.confirm(s.toString(), number);

            }
        });


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
        Log.d(TAG, "response: " + response.getToken());
        sessionManager.setAccessToken(response.getToken());
        sessionManager.setUserId(String.valueOf(response.getId()));
        sessionManager.setPhone(response.getPhone());
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @OnClick(R.id.sendButton)
    public void onSendCode() {
        if (verificationCodeEditText.getText().length() == 4) {
            String code = verificationCodeEditText.getText().toString();
            presenter.confirm(code, number);
        } else {
            Toast.makeText(getContext(), "XXXX", Toast.LENGTH_SHORT).show();
        }
    }

    public void startDown() {
        new CountDownTimer(30000, 1000) {

            //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
            public void onTick(long millisUntilFinished) {
                info.setText(R.string.info_counter);
                mTimer.setText(
                        +millisUntilFinished / 1000 + "  секунд");
                mTimer.setTextColor(Color.parseColor("#000000"));


            }

            //Задаем действия после завершения отсчета (высвечиваем надпись "Бабах!"):
            public void onFinish() {
                info.setText("");
                mTimer.setText(R.string.send_sms);
                mTimer.setTextColor(Color.parseColor("#457FFF"));
            }
        }
                .start();
    }

    @OnClick(R.id.textView21)
    public void onClickTimer() {
        presenter.signIn(number, API_KEY);
        startDown();
    }


}
