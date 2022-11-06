package com.sithackathon.caawi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EdgeEffect;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DonateActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogueBuilder;
    private AlertDialog dialog;
    private EditText cardNumber, expDate, ccv;
    private Button submitPayment;
    private CheckBox catOne, catTwo, catThree, catFour;
    Button m, a;
    FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        m = findViewById(R.id.btnMonthly);
        a = findViewById(R.id.btnAnnual);
        Map<String, Object> billing = new HashMap<>();

        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billing.put("plan", "monthly");
                fdb.collection("users").document(auth.getUid()).update(billing);

                paymentSetup();
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billing.put("plan", "annually");
                fdb.collection("users").document(auth.getUid()).update(billing);

                paymentSetup();
            }
        });
    }

    public void paymentSetup(){
        dialogueBuilder = new AlertDialog.Builder(this);
        final View PaymentView = getLayoutInflater().inflate(R.layout.activity_payment, null);

        cardNumber = PaymentView.findViewById(R.id.etCardNumber);
        expDate = PaymentView.findViewById(R.id.etExpDate);
        ccv = PaymentView.findViewById(R.id.etCCV);
        submitPayment = PaymentView.findViewById(R.id.btnSubmitPayment);

        catOne = PaymentView.findViewById(R.id.cbCatOne);
        catTwo = PaymentView.findViewById(R.id.cbCatTwo);
        catThree = PaymentView.findViewById(R.id.cbCatThree);
        catFour = PaymentView.findViewById(R.id.cbCatFour);

        dialogueBuilder.setView(PaymentView);
        dialog = dialogueBuilder.create();
        dialog.show();

        submitPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (catOne.isChecked()){
                    fdb.collection("users").document(auth.getUid()).update("categoryOne", "True");
                }
                if (catTwo.isChecked()){
                    fdb.collection("users").document(auth.getUid()).update("categoryTwo", "True");
                }
                if (catThree.isChecked()){
                    fdb.collection("users").document(auth.getUid()).update("categoryThree", "True");
                }
                if (catFour.isChecked()){
                    fdb.collection("users").document(auth.getUid()).update("categoryFour", "True");
                }
                Intent intent = new Intent(DonateActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}