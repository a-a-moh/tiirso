package com.sithackathon.caawi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class PlanActivity extends AppCompatActivity {


    ImageView year, month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        getSupportActionBar().hide();

        month = findViewById(R.id.imgBtnMonthly);
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String billing = "Monthly";
                Intent intent = new Intent(PlanActivity.this, RegisterActivity.class);
                intent.putExtra("plan", billing);
                startActivity(intent);
                finish();
                return;
            }
        });

        year = findViewById(R.id.imgBtnAnnually);
        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlanActivity.this, RegisterActivity.class);
                intent.putExtra("plan", "Annually");
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}