package com.codewithmischief.tempowala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class bookinghistory extends AppCompatActivity {

    TextView mFirstnamelastname,mMobnum,mPincode,mFlatno,mArea,mLandmark,mTown,mState;
    Button mBook;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinghistory);

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
        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("Booking").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                //putting if else fixed crashing
                if(e!=null) {
                    Log.d("Tag","Error:"+e.getMessage());
                }
                else {
                    mFirstnamelastname.setText(documentSnapshot.getString("Fullname"));
                    mMobnum.setText(documentSnapshot.getString("PhoneNo"));
                    mPincode.setText(documentSnapshot.getString("Pincode"));
                    mFlatno.setText(documentSnapshot.getString("Flatno"));
                    mArea.setText(documentSnapshot.getString("Area"));
                    mLandmark.setText(documentSnapshot.getString("Landmark"));
                    mTown.setText(documentSnapshot.getString("Town"));
                    mState.setText(documentSnapshot.getString("State"));
                }

            }
        });

        mBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder editbooking = new AlertDialog.Builder(view.getContext());
                editbooking.setTitle("Edit Booking?");
                editbooking.setMessage("You will be redirected to the booking page\nIf you don't wish to edit the details\nyou can press back button before pressing Book button!");
                editbooking.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(),bookingpage.class));
                    }
                });
                editbooking.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //close the dialog
                    }
                });

                editbooking.create().show();
            }
        });
    }
}