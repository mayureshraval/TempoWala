package com.codewithmischief.tempowala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splashscreen extends AppCompatActivity {
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        fAuth=FirebaseAuth.getInstance();
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(fAuth.getCurrentUser() != null) {
                    final FirebaseUser fUser=fAuth.getCurrentUser();
                    if(!fUser.isEmailVerified()){
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                    else {
                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                        finish();
                    }

                }
                else {
                    startActivity(new Intent(getApplicationContext(),Login.class));
                    finish();
                }
            }
        },3000);

    }
}