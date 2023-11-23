package com.locdhph46788.pro1211_nhom10_kinglaptop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Thread time = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e) {
                } finally {
                    Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        time.start();
    }
}