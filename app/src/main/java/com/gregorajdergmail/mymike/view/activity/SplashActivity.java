package com.gregorajdergmail.mymike.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gregorajdergmail.mymike.R;
import com.gregorajdergmail.mymike.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startMainActivity();
            }
        }, 1000);
    }

    private void startMainActivity() {
        Log.d();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
