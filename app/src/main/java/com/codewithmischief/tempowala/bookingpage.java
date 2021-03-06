package com.codewithmischief.tempowala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class bookingpage extends AppCompatActivity {

    EditText mFirstnamelastname,mMobnum,mPincode,mFlatno,mArea,mLandmark,mTown,mState,
             mFirstnamelastname2,mMobnum2,mPincode2,mFlatno2,mArea2,mLandmark2,mTown2,mState2;

    Button mBook;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingpage);

        //pickup
        mFirstnamelastname=findViewById(R.id.firstlastname);

        mMobnum=findViewById(R.id.mobnum);
        //setting phone no to only number input(no special characters)
        mMobnum.setInputType(InputType.TYPE_CLASS_NUMBER);
        mMobnum.setRawInputType(Configuration.KEYBOARD_12KEY);
        //

        mPincode=findViewById(R.id.pincode);
        //setting pin no to only number input(no special characters)
        mPincode.setInputType(InputType.TYPE_CLASS_NUMBER);
        mPincode.setRawInputType(Configuration.KEYBOARD_12KEY);
        //

        mFlatno=findViewById(R.id.flatno);
        mArea=findViewById(R.id.area);
        mLandmark=findViewById(R.id.landmark);
        mTown=findViewById(R.id.town);
        mState=findViewById(R.id.state);
        //destination
        mFirstnamelastname2=findViewById(R.id.firstlastname2);

        mMobnum2=findViewById(R.id.mobnum2);
        //setting phone no to only number input(no special characters)
        mMobnum2.setInputType(InputType.TYPE_CLASS_NUMBER);
        mMobnum2.setRawInputType(Configuration.KEYBOARD_12KEY);
        //

        mPincode2=findViewById(R.id.pincode2);
        //setting pin no to only number input(no special characters)
        mPincode2.setInputType(InputType.TYPE_CLASS_NUMBER);
        mPincode2.setRawInputType(Configuration.KEYBOARD_12KEY);
        //

        mFlatno2=findViewById(R.id.flatno2);
        mArea2=findViewById(R.id.area2);
        mLandmark2=findViewById(R.id.landmark2);
        mTown2=findViewById(R.id.town2);
        mState2=findViewById(R.id.state2);

        mBook=findViewById(R.id.editbook);
        progressBar=findViewById(R.id.progressBar5);

        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        mBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pickup
                String firstname = mFirstnamelastname.getText().toString().trim();
                String phoneno   = mMobnum.getText().toString().trim();
                String pincode   = mPincode.getText().toString().trim();
                String flatno   = mFlatno.getText().toString().trim();
                String area   = mArea.getText().toString().trim();
                String landmark   = mLandmark.getText().toString().trim();
                String town      = mTown.getText().toString().trim();
                String state     = mState.getText().toString().trim();
                //destination
                String firstname2 = mFirstnamelastname2.getText().toString().trim();
                String phoneno2   = mMobnum2.getText().toString().trim();
                String pincode2   = mPincode2.getText().toString().trim();
                String flatno2   = mFlatno2.getText().toString().trim();
                String area2   = mArea2.getText().toString().trim();
                String landmark2   = mLandmark2.getText().toString().trim();
                String town2      = mTown2.getText().toString().trim();
                String state2     = mState2.getText().toString().trim();

                //Validation
                //pickup
                if(TextUtils.isEmpty(firstname)){
                    mFirstnamelastname.setError("Fullname Required!");
                    return;
                }
                 if(TextUtils.isEmpty(phoneno)){
                    mMobnum.setError("Phone No. Required!");
                    return;
                }
                 if(phoneno.length()<10){
                    mMobnum.setError("Enter 10 digit number.");
                    return;
                }
                 if(TextUtils.isEmpty(pincode)){
                    mPincode.setError("Pincode Required!");
                    return;
                }
                if(pincode.length()!=6){
                    mPincode.setError("Enter Valid Pincode");
                    return;
                }

                 if(TextUtils.isEmpty(town)){
                    mTown.setError("City/Town/Village Required!");
                    return;
                }
                 if(TextUtils.isEmpty(state)){
                    mState.setError("State Required!");
                    return;
                }
                 //destination
                if(TextUtils.isEmpty(firstname2)){
                    mFirstnamelastname2.setError("Fullname Required!");
                    return;
                }
                if(TextUtils.isEmpty(phoneno2)){
                    mMobnum2.setError("Phone No. Required!");
                    return;
                }
                if(phoneno2.length()<10){
                    mMobnum2.setError("Enter 10 digit number.");
                    return;
                }
                if(TextUtils.isEmpty(pincode2)){
                    mPincode2.setError("Pincode Required!");
                    return;
                }
                if(pincode2.length()!=6){
                    mPincode2.setError("Enter Valid Pincode");
                    return;
                }

                if(TextUtils.isEmpty(town2)){
                    mTown2.setError("City/Town/Village Required!");
                    return;
                }
                if(TextUtils.isEmpty(state2)){
                    mState2.setError("State Required!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                 //saving data
                userID=fAuth.getCurrentUser().getUid();
                //creating a document reference creating a collection booking and making a new doc using user id
                DocumentReference documentReference = fstore.collection("Booking").document(userID);
                //creating a hashmap to send data
                Map<String,Object> book = new HashMap<>();
                //setting status
                book.put("Status -","Active");
                //pickup
                book.put("a1 - Fullname",firstname);
                book.put("a2 - PhoneNo",phoneno);
                book.put("a3 - Pincode",pincode);
                book.put("a4 - Flatno",flatno);
                book.put("a5 - Area",area);
                book.put("a6 - Landmark",landmark);
                book.put("a7 - Town",town);
                book.put("a8 - State",state);
                //destination
                book.put("d1 - Fullname2",firstname2);
                book.put("d2 - PhoneNo2",phoneno2);
                book.put("d3 - Pincode2",pincode2);
                book.put("d4 - Flatno2",flatno2);
                book.put("d5 - Area2",area2);
                book.put("d6 - Landmark2",landmark2);
                book.put("d7 - Town2",town2);
                book.put("d8 - State2",state2);

                //using the document reference to set user document
                documentReference.set(book).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(bookingpage.this, "Booking Successful", Toast.LENGTH_SHORT).show();
                        Log.d("Tag","onSuccess: Successfully booked for "+ userID);
                        progressBar.setVisibility(View.INVISIBLE);
                        //notification
                        Intent intent = new Intent(getApplicationContext(),bookinghistory.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        PendingIntent pi = PendingIntent.
                                getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_ONE_SHOT);
                        String Channel_Id="Default";
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),Channel_Id);
                        builder.setSmallIcon(R.drawable.ic_notify)
                                .setContentTitle("Booking Successful!")
                                .setContentText("Tap to View,Edit,Cancel!")
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
                        Toast.makeText(bookingpage.this, "Error!" + e.getMessage(), Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
                //creating backup collection
                DocumentReference newbackup =fstore.collection("Backup").document(userID);
                Map<String,Object> backup = new HashMap<>();
                backup.put("Status -","Active");
                //pickup
                backup.put("a1 - Fullname",firstname);
                backup.put("a2 - PhoneNo",phoneno);
                backup.put("a3 - Pincode",pincode);
                backup.put("a4 - Flatno",flatno);
                backup.put("a5 - Area",area);
                backup.put("a6 - Landmark",landmark);
                backup.put("a7 - Town",town);
                backup.put("a8 - State",state);
                //destination
                backup.put("d1 - Fullname2",firstname2);
                backup.put("d2 - PhoneNo2",phoneno2);
                backup.put("d3 - Pincode2",pincode2);
                backup.put("d4 - Flatno2",flatno2);
                backup.put("d5 - Area2",area2);
                backup.put("d6 - Landmark2",landmark2);
                backup.put("d7 - Town2",town2);
                backup.put("d8 - State2",state2);

                newbackup.set(backup);


            }
        });
    }
}