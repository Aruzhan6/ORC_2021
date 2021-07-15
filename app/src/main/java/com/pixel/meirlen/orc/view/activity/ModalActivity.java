package com.pixel.meirlen.orc.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pixel.meirlen.orc.MainActivity;
import com.pixel.meirlen.orc.R;


public class ModalActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_modal);


    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }
}
