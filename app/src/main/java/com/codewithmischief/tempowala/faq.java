package com.codewithmischief.tempowala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class faq extends AppCompatActivity {
TextView mfaq,mAns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        mfaq=findViewById(R.id.faq);
        mAns=findViewById(R.id.ans);

//        mfaq.setOnClickListener(new View.OnClickListener() {
//            int counter=0; //setting counter to count onclick
//            @Override
//            public void onClick(View view) {
//                ++counter; //incrementing counter first click
//                if(counter==1){
//                    mAns.setVisibility(View.VISIBLE);
//                }
//                //this sets mAns visible , but when i click on it again i want it to hide the text view
//                counter=0; //resetting the counter
//            }
//        });
        mfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAns.getVisibility()== View.VISIBLE){
                    mAns.setVisibility(View.GONE);
                }
                else
                    mAns.setVisibility(View.VISIBLE);
            }
        });

    }
}