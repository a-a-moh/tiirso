package com.sithackathon.caawi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class DriveAdapter extends ArrayAdapter<Drive> {
    private Context mContext;
    int mResource;
    public DriveAdapter(@NonNull Context context, int resource, @NonNull List<Drive> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String driveID = getItem(position).getDriveID();
        String driveName = getItem(position).getDriveName();
        String driveType = getItem(position).getDriveType();
        String driveCategory = getItem(position).getDriveCategory();
        String driveGoal = getItem(position).getDriveGoal();
        String driveDescription = getItem(position).getDriveDescription();
        FirebaseStorage fsb = FirebaseStorage.getInstance();
        StorageReference pathReference = fsb.getInstance().getReference().child("driveImage/").child(driveID + ".jpg");
        LayoutInflater inflator = LayoutInflater.from(mContext);
        convertView = inflator.inflate(mResource, parent, false);
        TextView tvDrive = (TextView) convertView.findViewById(R.id.tvCurrentDriveName);
        TextView tvDescription =(TextView) convertView.findViewById(R.id.tvCurrentDriveDescription);
        Button invest = (Button) convertView.findViewById(R.id.btnCurrentDriveDonate);
        Button type = (Button) convertView.findViewById(R.id.btnType);
        Button category = (Button) convertView.findViewById(R.id.btnCategory);
        Button goal = (Button) convertView.findViewById(R.id.btnGoal);
        ImageView imgDrive = (ImageView) convertView.findViewById(R.id.imgCurrentDriveImage);
        final long ONE_MEGABYTE = 1024 * 1024 * 5;
        pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bit = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imgDrive.setImageBitmap(bit);
                Drawable img = new BitmapDrawable(bit);
                //imgDrive.setBackground(img);
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.print(e);
            }
        });

        invest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PlanActivity.class);
                mContext.startActivity(intent);
            }
        });

        tvDrive.setText(driveName);
        type.setText(driveType);
        category.setText(driveCategory);
        goal.setText(driveGoal);
        tvDescription.setText(driveDescription);
        return convertView;
    }

}
