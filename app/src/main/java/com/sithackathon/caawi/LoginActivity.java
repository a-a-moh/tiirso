package com.sithackathon.caawi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private EditText dEmail, dPassword;
    private Button dLogin;

    private FirebaseAuth dAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        dAuth = FirebaseAuth.getInstance();

        dLogin = (Button) findViewById(R.id.btnDriverLogin);

        dEmail = (EditText) findViewById(R.id.dEmail);
        dPassword = (EditText) findViewById(R.id.dPassword);

        dLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = dEmail.getText().toString();
                final String password = dPassword.getText().toString();
                dAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "sign in error", Toast.LENGTH_SHORT).show();
                        }else{
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                            return;
                        }
                    }
                });

            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(LoginActivity.this, InfoActivity.class);
        startActivity(intent);
        finish();
        return;
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }

}
