package com.codewithmischief.tempowala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Joinus extends AppCompatActivity {
Button mSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinus);
        mSubmit=findViewById(R.id.submit);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Joinus.this, "Submitting..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}