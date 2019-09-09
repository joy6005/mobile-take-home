package com.joypanchal.rickandmorty.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.joypanchal.rickandmorty.R;

public class SplashActivity extends AppCompatActivity {

    Handler handler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, EpisodesListActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }

}
