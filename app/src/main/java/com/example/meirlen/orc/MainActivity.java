package com.example.meirlen.orc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.meirlen.orc.interactor.ChatInteractor;
import com.example.meirlen.orc.rest.model.Category;
import com.example.meirlen.orc.presenter.ChatPresenter;
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
        chatPresenter.getMessages("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ODksInRpbWVzdGFtcCI6MTUzMTEzNzYwOC42MDI2MDh9.-XdJaLVB6xmwc0nbzm2_iXGlAKZXnrRgOvGy4b8D6-Q");
    }

    @Override
    public void getChats(List<Category> messages) {
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
