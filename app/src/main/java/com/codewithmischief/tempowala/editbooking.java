package com.codewithmischief.tempowala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class editbooking extends AppCompatActivity {
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
        setContentView(R.layout.activity_editbooking);

        //pickup
        mFirstnamelastname=findViewById(R.id.firstlastname);
        mMobnum=findViewById(R.id.mobnum);
        mPincode=findViewById(R.id.pincode);
        mFlatno=findViewById(R.id.flatno);
        mArea=findViewById(R.id.area);
        mLandmark=findViewById(R.id.landmark);
        mTown=findViewById(R.id.town);
        mState=findViewById(R.id.state);
        //destination
        mFirstnamelastname2=findViewById(R.id.firstlastname2);
        mMobnum2=findViewById(R.id.mobnum2);
        mPincode2=findViewById(R.id.pincode2);
        mFlatno2=findViewById(R.id.flatno2);
        mArea2=findViewById(R.id.area2);
        mLandmark2=findViewById(R.id.landmark2);
        mTown2=findViewById(R.id.town2);
        mState2=findViewById(R.id.state2);

        mBook=findViewById(R.id.editbook);
        progressBar=findViewById(R.id.progressBar3);

        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();


        //setting text
        DocumentReference documentReference = fstore.collection("Booking").document(userID);
        //if the document exists then set text and make INVISIBLE, but if it doesn't exist  be INVISIBLE
        //i am a beginner i set this if condition it doesn't seem to work it just sets the textviews to VISIBLE please help
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                //putting if else fixed crashing
                if (e != null) {
                    Log.d("Tag", "Error:" + e.getMessage());
                } else {
                    //pickup


                    mFirstnamelastname.setText(documentSnapshot.getString("a1 - Fullname"));

                    mMobnum.setText(documentSnapshot.getString("a2 - PhoneNo"));

                    mPincode.setText(documentSnapshot.getString("a3 - Pincode"));

                    mFlatno.setText(documentSnapshot.getString("a4 - Flatno"));

                    mArea.setText(documentSnapshot.getString("a5 - Area"));

                    mLandmark.setText(documentSnapshot.getString("a6 - Landmark"));

                    mTown.setText(documentSnapshot.getString("a7 - Town"));

                    mState.setText(documentSnapshot.getString("a8 - State"));

                    //destination
                    mFirstnamelastname2.setText(documentSnapshot.getString("d1 - Fullname2"));

                    mMobnum2.setText(documentSnapshot.getString("d2 - PhoneNo2"));

                    mPincode2.setText(documentSnapshot.getString("d3 - Pincode2"));

                    mFlatno2.setText(documentSnapshot.getString("d4 - Flatno2"));

                    mArea2.setText(documentSnapshot.getString("d5 - Area2"));

                    mLandmark2.setText(documentSnapshot.getString("d6 - Landmark2"));

                    mTown2.setText(documentSnapshot.getString("d7 - Town2"));

                    mState2.setText(documentSnapshot.getString("d8 - State2"));


                }

            }
        });



        //booking
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
                    mPincode.setError("Enter Valid Pincode");
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
                //creating a document reference creating a collection users and making a new doc using user id
                DocumentReference documentReference = fstore.collection("Booking").document(userID);
                //creating a hashmap to send data
                Map<String,Object> book = new HashMap<>();
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
                        Toast.makeText(editbooking.this, "Booking Successful", Toast.LENGTH_SHORT).show();
                        Log.d("Tag","onSuccess: Successfully booked for "+ userID);
                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(editbooking.this, "Error!" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


                progressBar.setVisibility(View.GONE);
            }
        });
    }
}


