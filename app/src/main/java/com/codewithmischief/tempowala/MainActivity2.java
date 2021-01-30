package com.codewithmischief.tempowala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Redirecting to Booking page!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),bookingpage.class));
            }
        });

        bookhisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Redirecting to Booking History page!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),bookinghistory.class));
            }
        });

        joinusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Redirecting to JOIN US page!", Toast.LENGTH_SHORT).show();
            }
        });

        faqbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Redirecting to FAQ page!", Toast.LENGTH_SHORT).show();
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