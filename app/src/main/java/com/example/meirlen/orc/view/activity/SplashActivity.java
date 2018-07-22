package com.example.meirlen.orc.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.MainActivity;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.SessionManager;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity {


    @Inject
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        App.getInstance().createCategoryComponent().inject(this);

        new Handler().postDelayed(() -> {
            if (sessionManager.getShowIntro()) {
                Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                startActivity(intent);
                finish();
            } else {
                if (sessionManager.getAccessToken() == null) {
                    Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }


            }

        }, 1000);

    }
}