package com.codewithmischief.tempowala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class MainActivity2 extends AppCompatActivity {
    Button logout;
    TextView showname;
    String userId;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;

    ImageButton booknowbtn,bookhisbtn,joinusbtn,faqbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        showname=findViewById(R.id.namelogout);

        booknowbtn=findViewById(R.id.bookNow);
        bookhisbtn=findViewById(R.id.bookinHistory);
        joinusbtn=findViewById(R.id.joinUs);
        faqbtn=findViewById(R.id.faq);

        DocumentReference documentReference = fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                //putting if else fixed crashing
                if(e!=null) {
                    Log.d("Tag","Error:"+e.getMessage());
                }
                else {
                    showname.setText(documentSnapshot.getString("fName"));

                }

            }
        });

        booknowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //checking if internet is on
                //initializing connectivity manager
                ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                //getting active network info
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                //check net status
                if(networkInfo==null || !networkInfo.isConnected() || !networkInfo.isAvailable()){
                    AlertDialog.Builder NoInternet = new AlertDialog.Builder(view.getContext());
                    NoInternet.setTitle("No connection!")
                            .setMessage("No internet connection!\nDo you want to book with Sms?\nNote:SMS Charges may apply as per your provider.")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //booking via sms
                                    startActivity(new Intent(getApplicationContext(),smsbooking.class));
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //do nothing
                        }
                    });
                    NoInternet.create().show();
                    Toast.makeText(MainActivity2.this, "No Connection!", Toast.LENGTH_SHORT).show();
                }
                else {
                    //only way to check if it exists join us page
                    fstore.collection("Booking").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.getResult().exists()) {
                                AlertDialog.Builder existsNotice = new AlertDialog.Builder(view.getContext());
                                existsNotice.setTitle("Booking Already Exists!")
                                        .setMessage("If your previous booking is COMPLETED then,\nOn the Home Page Click:\n1.Booking History>\n2.Scroll Down>\n3.Delete>\n4.Yes")
                                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //Do nothing
                                            }
                                        });
                                existsNotice.create().show();

                            } else {
                                Toast.makeText(MainActivity2.this, "Redirecting to Booking page!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), bookingpage.class));
                            }
                        }

                    });
                }

//                //creating new document ref becoz i have one above
//                DocumentReference  docref = fstore.collection("Booking").document(userId);
//                docref.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//                         if (e!=null) {
//                             Log.d("Tag1", "Error1:" + e.getMessage());
//                             Toast.makeText(MainActivity2.this, "Booking exists", Toast.LENGTH_SHORT).show();
//                         }
//                         else{
//                             Toast.makeText(MainActivity2.this, "Redirecting to Booking page!", Toast.LENGTH_SHORT).show();
//                             startActivity(new Intent(getApplicationContext(),bookingpage.class));
//                         }
//                    }
//                });
            }
        });

        bookhisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //checking if internet is on
                //initializing connectivity manager
                ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                //getting active network info
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                //check net status
                if(networkInfo==null || !networkInfo.isConnected() || !networkInfo.isAvailable()){
                    Toast.makeText(MainActivity2.this, "Turn on internet to continue", Toast.LENGTH_SHORT).show();
                }
                //
                else {
                    Toast.makeText(MainActivity2.this, "Redirecting to Booking History page!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), bookinghistory.class));
                }
            }
        });

        joinusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checking if internet is on
                //initializing connectivity manager
                ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                //getting active network info
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                //check net status
                if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
                    Toast.makeText(MainActivity2.this, "Turn on internet to continue", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Redirecting to JOIN US page!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Joinus.class));
                }
            }
        });

        faqbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Redirecting to FAQ page!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),faq.class));
            }
        });

        logout=findViewById(R.id.mainLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

    }
}