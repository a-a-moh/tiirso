package com.sithackathon.caawi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class PaymentSuccessActivity extends AppCompatActivity {

    Handler handle = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_payment_success);
        //getSupportActionBar().hide();

        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PaymentSuccessActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        }, 3000);
    }
}