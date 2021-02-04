package com.codewithmischief.tempowala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class faq extends AppCompatActivity {
TextView mfaq,mAns,mfaq2,mAns2,mfaq3,mAns3,mfaq4,mAns4,mfaq5,mAns5,mfaq6,mAns6,mfaq7,mAns7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        mfaq=findViewById(R.id.faq1);
        mAns=findViewById(R.id.ans);
        mfaq2=findViewById(R.id.faq2);
        mAns2=findViewById(R.id.ans2);
        mfaq3=findViewById(R.id.faq3);
        mAns3=findViewById(R.id.ans3);
        mfaq4=findViewById(R.id.faq4);
        mAns4=findViewById(R.id.ans4);
        mfaq5=findViewById(R.id.faq5);
        mAns5=findViewById(R.id.ans5);
        mfaq6=findViewById(R.id.faq6);
        mAns6=findViewById(R.id.ans6);
        mfaq7=findViewById(R.id.faq7);
        mAns7=findViewById(R.id.ans7);

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
//                    ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) mfaq2.getLayoutParams();
//                    marginParams.setMargins(marginParams.leftMargin,
//                            20, //setting it back to 0
//                            marginParams.rightMargin,
//                            marginParams.bottomMargin);

                    mAns.setVisibility(View.GONE);
                }
                else
                    mAns.setVisibility(View.VISIBLE);
//                ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) mfaq2.getLayoutParams();
//                marginParams.setMargins(marginParams.leftMargin,
//                        400, //only changing top margin
//                        marginParams.rightMargin,
//                        marginParams.bottomMargin);
            }
        });
        mfaq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAns2.getVisibility()== View.VISIBLE){
                    mAns2.setVisibility(View.GONE);
                }
                else
                    mAns2.setVisibility(View.VISIBLE);
            }
        });

        mfaq3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAns3.getVisibility()== View.VISIBLE){
                    mAns3.setVisibility(View.GONE);
                }
                else
                    mAns3.setVisibility(View.VISIBLE);
            }
        });
        mfaq4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAns4.getVisibility()== View.VISIBLE){
                    mAns4.setVisibility(View.GONE);
                }
                else
                    mAns4.setVisibility(View.VISIBLE);
            }
        });
        mfaq5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAns5.getVisibility()== View.VISIBLE){
                    mAns5.setVisibility(View.GONE);
                }
                else
                    mAns5.setVisibility(View.VISIBLE);
            }
        });
        mfaq6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAns6.getVisibility()== View.VISIBLE){
                    mAns6.setVisibility(View.GONE);
                }
                else
                    mAns6.setVisibility(View.VISIBLE);
            }
        });
        mfaq7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAns7.getVisibility()== View.VISIBLE){
                    mAns7.setVisibility(View.GONE);
                }
                else
                    mAns7.setVisibility(View.VISIBLE);
            }
        });
    }
}