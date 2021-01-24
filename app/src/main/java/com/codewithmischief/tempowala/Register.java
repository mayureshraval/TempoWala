package com.codewithmischief.tempowala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    public static final String TAG = "Tag";
    //declaring java variables to get xml values
    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegisterBtn;
    TextView login;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
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
        fstore=FirebaseFirestore.getInstance();
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
                final String fullName = mFullName.getText().toString().trim();
                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                final String phone= mPhone.getText().toString().trim();
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

                if(TextUtils.isEmpty(phone)){
                    mPhone.setError("Phone No. Required");
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

                            //saving data to cloud firestore
                            userID=fAuth.getCurrentUser().getUid(); //getting the current users user id
                            //creating a document reference creating a collection users and making a new doc using user id
                            DocumentReference documentReference = fstore.collection("users").document(userID);
                            //creating a hashmap to send data
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",fullName);
                            user.put("email",email);
                            user.put("password",password);
                            user.put("phone",phone);

                            //using the document referene to set user document
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"onSuccess: user profiled created for "+ userID);
                                }
                            });

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