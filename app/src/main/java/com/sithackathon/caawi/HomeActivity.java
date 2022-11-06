package com.sithackathon.caawi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ArrayList<Drive> donationDrivers = new ArrayList<>();
    ListView driveList;
    Button lg, p;
    private FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        driveList = (ListView) findViewById(R.id.driveView);

        fdb.collection("drives").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot q : queryDocumentSnapshots){
                    Drive d = new Drive(q.getId());
                    d.setDriveName(q.get("name").toString());
                    d.setDriveDescription(q.get("description").toString());
                    //d.setDriveImage(q.get("driveName").toString());
                    donationDrivers.add(d);
                    setL();
                }
            }
        });
    }
    public void setL(){
        DriveAdapter adapter = new DriveAdapter(this, R.layout.adapter_driver_layout, donationDrivers);
        driveList.setAdapter(adapter);
    }
}