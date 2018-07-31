package com.example.meirlen.orc.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.meirlen.orc.App;
import com.example.meirlen.orc.MainActivity;
import com.example.meirlen.orc.R;
import com.example.meirlen.orc.helper.SessionManager;

import javax.inject.Inject;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.MessageButtonBehaviour;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import agency.tango.materialintroscreen.animations.IViewTranslation;

public class IntroActivity extends MaterialIntroActivity {

    @Inject
    SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableLastSlideAlphaExitTransition(true);
        App.getInstance().createCategoryComponent().inject(this);
        sessionManager.setShowIntro(false);
        getBackButtonTranslationWrapper()
                .setEnterTranslation(View::setAlpha);

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.first_slide_background)
                .buttonsColor(R.color.first_slide_buttons)
                .image(R.drawable.img_slide_one)
                .title("ОРЦ")
                .description("Сервис доставки продуктов")
                .build());


        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.second_slide_background)
                .buttonsColor(R.color.second_slide_buttons)
                .image(R.drawable.img_slide_two)
                .title("Доставка продуктов ")
                .description("из METRO, MAGNUM ")
                .build());

        //  addSlide(new CustomSlide());


        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.fourth_slide_background)
                .buttonsColor(R.color.fourth_slide_buttons)
                .image(R.drawable.img_slide_one)
                .title("Огромный выбор товаров")
                .description("Более 80 000 товаров из 60 гипермаркетов Караганды!")
                .build());
    }

    @Override
    public void onFinish() {
        super.onFinish();
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);

    }
}