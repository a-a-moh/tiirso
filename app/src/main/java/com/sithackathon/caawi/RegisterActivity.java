package com.sithackathon.caawi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText name, cardNumber, expDate, cvc, email, password, confirmPassword;
    private Button saveDetails;
    private FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    FirebaseAuth auth;
    HashMap<String, String> user = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.etFullName);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
        confirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        saveDetails = (Button) findViewById(R.id.saveDetails);
        Intent intent = getIntent();
        saveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (password.getText().toString().equals(confirmPassword.getText().toString())){
                    saveDriver();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Error creating account", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveDriver() {

        String emailAddress = email.getText().toString();
        String upassword = password.getText().toString();
        String fullName = name.getText().toString();
        Intent intent = getIntent();
        String bill = intent.getStringExtra("plan");


        if (emailAddress.isEmpty() || upassword.isEmpty() || fullName.isEmpty()){
            Toast.makeText(this, "Please enter all the details requested", Toast.LENGTH_LONG);
        }else{
            auth.createUserWithEmailAndPassword(emailAddress, upassword).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "sign up error", Toast.LENGTH_SHORT).show();
                    }else{
                        String userID = auth.getCurrentUser().getUid();
                        user.put("fullName", fullName);
                        user.put("plan", bill);
                        fdb.collection("users").document(auth.getUid()).set(user);
                        Intent intent = new Intent(RegisterActivity.this, PaymentSuccessActivity.class);
                        startActivity(intent);
                        finish();
                        return;
                    }
                }
            });
        }

    }

}
