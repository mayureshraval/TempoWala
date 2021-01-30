package com.codewithmischief.tempowala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class bookinghistory extends AppCompatActivity {

    TextView mFirstnamelastname,mMobnum,mPincode,mFlatno,mArea,mLandmark,mTown,mState,
             mFirstnamelastname2,mMobnum2,mPincode2,mFlatno2,mArea2,mLandmark2,mTown2,mState2,
             mYourpickup,mYourdestination;
    Button mEdit,mCancel;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinghistory);
        mYourpickup = findViewById(R.id.yourpickup);
        mYourdestination = findViewById(R.id.yourdestination);
        //pickup
        mFirstnamelastname = findViewById(R.id.firstlastname);
        mMobnum = findViewById(R.id.mobnum);
        mPincode = findViewById(R.id.pincode);
        mFlatno = findViewById(R.id.flatno);
        mArea = findViewById(R.id.area);
        mLandmark = findViewById(R.id.landmark);
        mTown = findViewById(R.id.town);
        mState = findViewById(R.id.state);
        //destination
        mFirstnamelastname2 = findViewById(R.id.firstlastname2);
        mMobnum2 = findViewById(R.id.mobnum2);
        mPincode2 = findViewById(R.id.pincode2);
        mFlatno2 = findViewById(R.id.flatno2);
        mArea2 = findViewById(R.id.area2);
        mLandmark2 = findViewById(R.id.landmark2);
        mTown2 = findViewById(R.id.town2);
        mState2 = findViewById(R.id.state2);

        mEdit = findViewById(R.id.editbook);
        mCancel=findViewById(R.id.cancelbook);
        progressBar = findViewById(R.id.progressBar3);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();



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

//                            mYourpickup.setVisibility(View.VISIBLE);
//                            mYourdestination.setVisibility(View.VISIBLE);
//                            mFirstnamelastname.setVisibility(View.VISIBLE);
//                            mMobnum.setVisibility(View.VISIBLE);
//                            mPincode.setVisibility(View.VISIBLE);
//                            mFlatno.setVisibility(View.VISIBLE);
//                            mArea.setVisibility(View.VISIBLE);
//                            mLandmark.setVisibility(View.VISIBLE);
//                            mTown.setVisibility(View.VISIBLE);
//                            mState.setVisibility(View.VISIBLE);
//                            mFirstnamelastname2.setVisibility(View.VISIBLE);
//                            mMobnum2.setVisibility(View.VISIBLE);
//                            mPincode2.setVisibility(View.VISIBLE);
//                            mFlatno2.setVisibility(View.VISIBLE);
//                            mArea2.setVisibility(View.VISIBLE);
//                            mLandmark2.setVisibility(View.VISIBLE);
//                            mTown2.setVisibility(View.VISIBLE);
//                            mState2.setVisibility(View.VISIBLE);
//                            mEdit.setVisibility(View.VISIBLE);


                        }

                    }
                });




        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder editbooking = new AlertDialog.Builder(view.getContext());
                editbooking.setTitle("Edit Booking?");
                editbooking.setMessage("You will be redirected to the booking page\nIf you don't wish to edit the details\nyou can press back button before pressing Book button!");
                editbooking.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(),editbooking.class));
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

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder cancelbooking = new AlertDialog.Builder(view.getContext());
                cancelbooking.setTitle("Cancel Booking?");
                cancelbooking.setMessage("Your booking will be cancelled\nPress Yes to Confirm\nPress No to stop");
                cancelbooking.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fstore.collection("Booking").document(userID)
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(bookinghistory.this, "Booking successfully cancelled!", Toast.LENGTH_LONG).show();
                                        Log.d("Tag", "DocumentSnapshot successfully deleted!");
                                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("Tag", "Error deleting document", e);
                                    }
                                });
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing
                    }
                });
                cancelbooking.create().show();
            }
        });
    }
}