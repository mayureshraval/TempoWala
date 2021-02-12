package com.codewithmischief.tempowala;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.protobuf.StringValue;

public class faq extends AppCompatActivity {
TextView mfaq,mAns,mfaq2,mAns2,mfaq3,mAns3,mfaq4,mAns4,mfaq5,mAns5,mfaq6,mAns6,mfaq7,mAns7,mfaq8,mAns8,mfaq9,mAns9,mfaq10,mAns10;
    public final String KM_HINT="Enter Km";
    public final String FARE_HINT="\nResult";
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
        mfaq8=findViewById(R.id.faq8);
        mAns8=findViewById(R.id.ans8);
        mfaq9=findViewById(R.id.faq9);
        mAns9 =findViewById(R.id.ans9);
        mfaq10=findViewById(R.id.faq10);
        mAns10=findViewById(R.id.ans10);

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
        mAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder farecalculator = new AlertDialog.Builder(view.getContext());
                        farecalculator.setTitle(R.string.Title)
                        .setMessage(R.string.Message);


                // Set an EditText view to get user input
                final EditText KM = new EditText(view.getContext());
                KM.setInputType(InputType.TYPE_CLASS_NUMBER);
                KM.setRawInputType(Configuration.KEYBOARD_12KEY);
                KM.setHint(KM_HINT);
                final TextView FARE = new TextView(view.getContext());
                FARE.setHint(FARE_HINT);
                FARE.setPadding(5,5,5,5);
                FARE.setTextSize(18);
                LinearLayout layout = new LinearLayout(getApplicationContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(KM);
                layout.addView(FARE);
                farecalculator.setView(layout);

                farecalculator.setPositiveButton("Calculate",null)
                        .setNegativeButton("Close",null);
                final AlertDialog dialog=farecalculator.create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /*
                        so what happened here was the double value is a primitive data type which cannot be null thus cannot be compared to null.
                        so the app was crashing when clicked on alert builder positive button when the data was null
                        so i took the value into a string first checked it if it was empty then if it wasn't empty i parsed it to double value to execute further
                        took me 10 hours to figure out this one. started at morning 10 , 9:34 i solved it lol.
                        */
                        String nullCheck=KM.getText().toString();
                        if(TextUtils.isEmpty(nullCheck)){
                            Toast.makeText(faq.this, "Enter KM or Press Close!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Double n1,Result;
                            n1=Double.parseDouble(nullCheck);

                            if(n1==0){
                                FARE.setText("\nRs. 0");
                            }
                            else if(n1==1){
                                FARE.setText("\nRs.500");
                            }
                            else if(n1==2){
                                FARE.setText("\nRs.700");
                            }
                            else{
                                Result=n1*300;
                                FARE.setText("\nRs."+ Result);
                            }
                        }
                    }
                });


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
        mfaq8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAns8.getVisibility()== View.VISIBLE){
                    mAns8.setVisibility(View.GONE);
                }
                else
                    mAns8.setVisibility(View.VISIBLE);
            }
        });
        mfaq9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAns9.getVisibility()== View.VISIBLE){
                    mAns9.setVisibility(View.GONE);
                }
                else
                    mAns9.setVisibility(View.VISIBLE);
                    mAns9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String[] ourMail = {"tempowalaofficial@gmail.com"};
//                            String[] cc = {"tempowalaofficial@gmail.com"};
//                            String[] bcc = {"tempowalaofficial@gmail.com"};
                            Intent intent =new Intent(Intent.ACTION_SENDTO);
                            intent.setData(Uri.parse("mailto:"));
                            intent.putExtra(Intent.EXTRA_EMAIL, ourMail);
//                            intent.putExtra(Intent.EXTRA_CC, cc);
//                            intent.putExtra(Intent.EXTRA_BCC, bcc);
                            intent.putExtra(Intent.EXTRA_SUBJECT,"Reason to contact here..");
                            intent.putExtra(Intent.EXTRA_TEXT,"Full Name:\n Phone No:(Optional) \nExplain in detail here..");

                            startActivity(Intent.createChooser(intent,"Choose Application To Send Email"));

                        }
                    });
            }
        });
        mfaq10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAns10.getVisibility()== View.VISIBLE){
                    mAns10.setVisibility(View.GONE);
                }
                else {
                    mAns10.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}