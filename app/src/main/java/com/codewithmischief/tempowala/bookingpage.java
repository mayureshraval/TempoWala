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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class bookingpage extends AppCompatActivity {

    EditText mFirstnamelastname,mMobnum,mPincode,mFlatno,mArea,mLandmark,mTown,mState;
    Button mBook;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingpage);

        mFirstnamelastname=findViewById(R.id.firstlastname);
        mMobnum=findViewById(R.id.mobnum);
        mPincode=findViewById(R.id.pincode);
        mFlatno=findViewById(R.id.flatno);
        mArea=findViewById(R.id.area);
        mLandmark=findViewById(R.id.landmark);
        mTown=findViewById(R.id.town);
        mState=findViewById(R.id.state);
        mBook=findViewById(R.id.book);
        progressBar=findViewById(R.id.progressBar3);

        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        mBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = mFirstnamelastname.getText().toString().trim();
                String phoneno   = mMobnum.getText().toString().trim();
                String pincode   = mPincode.getText().toString().trim();
                String flatno   = mFlatno.getText().toString().trim();
                String area   = mArea.getText().toString().trim();
                String landmark   = mLandmark.getText().toString().trim();
                String town      = mTown.getText().toString().trim();
                String state     = mState.getText().toString().trim();



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

                progressBar.setVisibility(View.VISIBLE);

                 //saving data
                userID=fAuth.getCurrentUser().getUid();
                //creating a document reference creating a collection users and making a new doc using user id
                DocumentReference documentReference = fstore.collection("Booking").document(userID);
                //creating a hashmap to send data
                Map<String,Object> book = new HashMap<>();
                book.put("Fullname",firstname);
                book.put("PhoneNo",phoneno);
                book.put("Pincode",pincode);
                book.put("Flatno",flatno);
                book.put("Area",area);
                book.put("Landmark",landmark);
                book.put("Town",town);
                book.put("State",state);

                //using the document reference to set user document
                documentReference.set(book).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(bookingpage.this, "Booking Successful", Toast.LENGTH_SHORT).show();
                        Log.d("Tag","onSuccess: Successfully booked for "+ userID);
                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(bookingpage.this, "Error!" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


                progressBar.setVisibility(View.GONE);
            }
        });
    }
}