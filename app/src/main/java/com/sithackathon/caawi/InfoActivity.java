package com.sithackathon.caawi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class InfoActivity extends AppCompatActivity {

    private Button login, browse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_info);

        login = findViewById(R.id.btnLog);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        browse = findViewById(R.id.btnBrowse);
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}