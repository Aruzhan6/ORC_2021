package com.example.meirlen.orc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.meirlen.orc.interactor.ChatInteractor;
import com.example.meirlen.orc.interactor.ChatInteractorImpl;
import com.example.meirlen.orc.model.Chat;
import com.example.meirlen.orc.presenter.ChatPresenter;
import com.example.meirlen.orc.presenter.impl.ChatPresenterImpl;
import com.example.meirlen.orc.view.ChatView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ChatView {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @Inject
    ChatPresenter chatPresenter;

    @Inject
    ChatInteractor chatInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        App.getInstance().createChatComponent().inject(this);
        chatPresenter.setView(this);
        chatPresenter.getMessages("Token");
    }

    @Override
    public void getChats(List<Chat> messages) {
        Toast.makeText(this, "Количество обьектов: " + String.valueOf(messages.size()), Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
