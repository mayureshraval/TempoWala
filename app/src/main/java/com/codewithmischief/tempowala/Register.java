package com.codewithmischief.tempowala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    //declaring java variables to get xml values
    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegisterBtn;
    TextView login;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //pulling values from xml to java variables
        mFullName = findViewById(R.id.reg_fullname);
        mEmail = findViewById(R.id.reg_email);
        mPassword = findViewById(R.id.reg_pass);
        mPhone = findViewById(R.id.reg_phone);
        mRegisterBtn = findViewById(R.id.reg_btn1);
        login = findViewById(R.id.reg_login);

        //firebase instance
        fAuth=FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        //if already logged in then send to main activity
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        //when clicked on signup validate
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String phone= mPhone.getText().toString().trim();
                String character = "@";
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email Required");
                    return;
                }

                if(!email.contains(character))
                {
                    mEmail.setError("Enter Valid Email");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password Required");
                    return;
                }

                if(password.length()<8){
                    mPassword.setError("Minimum 8 characters");
                    return;
                }
                if(phone.length()<10){
                    mPhone.setError("Invalid Phone No.");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register the user in database
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                        else{
                            Toast.makeText(Register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}