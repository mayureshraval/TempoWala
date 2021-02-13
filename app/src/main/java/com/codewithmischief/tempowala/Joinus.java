package com.codewithmischief.tempowala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Joinus extends AppCompatActivity {
Button mSubmit;
EditText mtempofullname,mtempophone,mtempoaadhar,mtempodl,mtempocity,mtempostate;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    String userID;
    TextView mExists,mNotice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinus);
        mtempofullname=findViewById(R.id.tempowalaname);
        mtempophone=findViewById(R.id.tempowalaphone);
        mtempoaadhar=findViewById(R.id.tempowalaadhar);
        mtempodl=findViewById(R.id.tempowaladl);
        mtempocity=findViewById(R.id.tempowalacity);
        mtempostate=findViewById(R.id.tempowalastate);
        mExists=findViewById(R.id.alreadyexist);
        mNotice=findViewById(R.id.notice);
        fAuth=FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();

        mSubmit=findViewById(R.id.submit);
        userID=fAuth.getCurrentUser().getUid();
        //cheking if the doc already exists
        fstore.collection("Applications").document(userID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.getResult().exists()){
                    mtempofullname.setVisibility(View.INVISIBLE);
                    mtempophone.setVisibility(View.INVISIBLE);
                    mtempoaadhar.setVisibility(View.INVISIBLE);
                    mtempodl.setVisibility(View.INVISIBLE);
                    mtempocity.setVisibility(View.INVISIBLE);
                    mtempostate.setVisibility(View.INVISIBLE);
                    mSubmit.setVisibility(View.INVISIBLE);
                    mtempofullname.setVisibility(View.INVISIBLE);
                    mNotice.setVisibility(View.INVISIBLE);
                    mExists.setVisibility(View.VISIBLE);
                }
                else{
                    mExists.setVisibility(View.INVISIBLE);
                    //do nothing
                }
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Joinus.this, "Submitting..", Toast.LENGTH_SHORT).show();

                String fullname=mtempofullname.getText().toString().trim();
                String phone=mtempophone.getText().toString().trim();
                String aadhar=mtempoaadhar.getText().toString().trim();
                String dl=mtempodl.getText().toString().trim();
                String city=mtempocity.getText().toString().trim();
                String state=mtempostate.getText().toString().trim();

                if (TextUtils.isEmpty(fullname)){
                    mtempofullname.setError("Fullname Required");
                    return;
                }
                if (phone.length()<10){
                    mtempophone.setError("Invalid PhoneNo.");
                    return;
                }
                if(aadhar.length()<12){
                    mtempoaadhar.setError("Invalid AADHAAR");
                    return;
                }
                if(dl.length()<15){
                    mtempodl.setError("Invalid Driving License");
                    return;
                }
                if (TextUtils.isEmpty(city)){
                    mtempocity.setError("City Required");
                    return;
                }
                if(TextUtils.isEmpty(state)){
                    mtempostate.setError("State Required");
                    return;
                }



                DocumentReference docref = fstore.collection("Applications").document(userID);


                Map<String,Object> req = new HashMap<>();
                req.put("1:Fullname - ",fullname);
                req.put("2:Phone - ",phone);
                req.put("3:Aadhaar - ",aadhar);
                req.put("4:DrivingLicense - ",dl);
                req.put("5:City - ",city);
                req.put("6:State - ",state);

                docref.set(req).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Joinus.this, "Submit Successful!", Toast.LENGTH_SHORT).show();

                        //notification
                        Intent intent = new Intent(getApplicationContext(),Joinus.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        PendingIntent pi = PendingIntent.
                                getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_ONE_SHOT);
                        String Channel_Id="Default";
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),Channel_Id);
                        builder.setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle("Application Submitted!")
                                .setContentText("Tap to View!")
                                .setAutoCancel(true)
                                .setContentIntent(pi);
                        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                        {
                            NotificationChannel channel=new NotificationChannel(
                                    Channel_Id,"Default channel",NotificationManager.IMPORTANCE_DEFAULT);
                            manager.createNotificationChannel(channel);
                        }
                        manager.notify(0,builder.build());

                        //above for notification


                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Joinus.this, "Submit Failed!"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}