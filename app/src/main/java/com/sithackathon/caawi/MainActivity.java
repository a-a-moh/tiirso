package com.sithackathon.caawi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Drive> donationDrivers = new ArrayList<>();
    ListView driveList;
    Button login, register, invest;
    private FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        driveList = (ListView) findViewById(R.id.driveView);

//        invest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//                startActivity(intent);
//                finish();
//                return;
//            }
//        });

        /*DatabaseReference cu = FirebaseDatabase.getInstance().getReference();
        cu.setValue(true);*/
        fdb.collection("drives").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot q : queryDocumentSnapshots){
                    Drive d = new Drive(q.getId());
                    d.setDriveName(q.get("name").toString());
                    d.setDriveDescription(q.get("description").toString());
                    d.setDriveImage(q.getId());
                    d.setDriveType(q.get("type").toString());
                    d.setDriveGoal(q.get("goal").toString());
                    d.setDriveCategory(q.get("category").toString());
                    donationDrivers.add(d);
                    setL();
                }
            }
        });

    }
    public void setL(){
        DriveAdapter adapter = new DriveAdapter(this, R.layout.adapter_driver_layout, donationDrivers);
        driveList.setDivider(null);
        driveList.setDividerHeight(0);
        driveList.setAdapter(adapter);
    }

    public static void register(){
    }
}