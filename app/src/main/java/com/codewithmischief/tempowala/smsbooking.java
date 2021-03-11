package com.codewithmischief.tempowala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class smsbooking extends AppCompatActivity {

    EditText mFirstnamelastname,mMobnum,mPincode,mFlatno,mArea,mLandmark,mTown,mState,
            mFirstnamelastname2,mMobnum2,mPincode2,mFlatno2,mArea2,mLandmark2,mTown2,mState2;

    Button mBook;
    ProgressBar progressBar;

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsbooking);

        //checking permission

        if(checkPermission(Manifest.permission.SEND_SMS)){
            //donothing
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }

        //

        //pickup
        mFirstnamelastname=findViewById(R.id.firstlastname);

        mMobnum=findViewById(R.id.mobnum);
        //setting phone no to only number input(no special characters)
        mMobnum.setInputType(InputType.TYPE_CLASS_NUMBER);
        mMobnum.setRawInputType(Configuration.KEYBOARD_12KEY);
        //

        mPincode=findViewById(R.id.pincode);
        //setting pin no to only number input(no special characters)
        mPincode.setInputType(InputType.TYPE_CLASS_NUMBER);
        mPincode.setRawInputType(Configuration.KEYBOARD_12KEY);
        //

        mFlatno=findViewById(R.id.flatno);
        mArea=findViewById(R.id.area);
        mLandmark=findViewById(R.id.landmark);
        mTown=findViewById(R.id.town);
        mState=findViewById(R.id.state);
        //destination
        mFirstnamelastname2=findViewById(R.id.firstlastname2);

        mMobnum2=findViewById(R.id.mobnum2);
        //setting phone no to only number input(no special characters)
        mMobnum2.setInputType(InputType.TYPE_CLASS_NUMBER);
        mMobnum2.setRawInputType(Configuration.KEYBOARD_12KEY);
        //

        mPincode2=findViewById(R.id.pincode2);
        //setting pin no to only number input(no special characters)
        mPincode2.setInputType(InputType.TYPE_CLASS_NUMBER);
        mPincode2.setRawInputType(Configuration.KEYBOARD_12KEY);
        //

        mFlatno2=findViewById(R.id.flatno2);
        mArea2=findViewById(R.id.area2);
        mLandmark2=findViewById(R.id.landmark2);
        mTown2=findViewById(R.id.town2);
        mState2=findViewById(R.id.state2);

        mBook=findViewById(R.id.editbook);
        progressBar=findViewById(R.id.progressBar5);


        mBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pickup
                String firstname = mFirstnamelastname.getText().toString().trim();
                String phoneno = mMobnum.getText().toString().trim();
                String pincode = mPincode.getText().toString().trim();
                String flatno = mFlatno.getText().toString().trim();
                String area = mArea.getText().toString().trim();
                String landmark = mLandmark.getText().toString().trim();
                String town = mTown.getText().toString().trim();
                String state = mState.getText().toString().trim();
                //destination
                String firstname2 = mFirstnamelastname2.getText().toString().trim();
                String phoneno2 = mMobnum2.getText().toString().trim();
                String pincode2 = mPincode2.getText().toString().trim();
                String flatno2 = mFlatno2.getText().toString().trim();
                String area2 = mArea2.getText().toString().trim();
                String landmark2 = mLandmark2.getText().toString().trim();
                String town2 = mTown2.getText().toString().trim();
                String state2 = mState2.getText().toString().trim();

                //Validation
                //pickup
                if (TextUtils.isEmpty(firstname)) {
                    mFirstnamelastname.setError("Fullname Required!");
                    return;
                }
                if (TextUtils.isEmpty(phoneno)) {
                    mMobnum.setError("Phone No. Required!");
                    return;
                }
                if (phoneno.length() < 10) {
                    mMobnum.setError("Enter 10 digit number.");
                    return;
                }
                if (TextUtils.isEmpty(pincode)) {
                    mPincode.setError("Pincode Required!");
                    return;
                }
                if (pincode.length() != 6) {
                    mPincode.setError("Enter Valid Pincode");
                    return;
                }

                if (TextUtils.isEmpty(town)) {
                    mTown.setError("City/Town/Village Required!");
                    return;
                }
                if (TextUtils.isEmpty(state)) {
                    mState.setError("State Required!");
                    return;
                }
                //destination
                if (TextUtils.isEmpty(firstname2)) {
                    mFirstnamelastname2.setError("Fullname Required!");
                    return;
                }
                if (TextUtils.isEmpty(phoneno2)) {
                    mMobnum2.setError("Phone No. Required!");
                    return;
                }
                if (phoneno2.length() < 10) {
                    mMobnum2.setError("Enter 10 digit number.");
                    return;
                }
                if (TextUtils.isEmpty(pincode2)) {
                    mPincode2.setError("Pincode Required!");
                    return;
                }
                if (pincode2.length() != 6) {
                    mPincode2.setError("Enter Valid Pincode");
                    return;
                }

                if (TextUtils.isEmpty(town2)) {
                    mTown2.setError("City/Town/Village Required!");
                    return;
                }
                if (TextUtils.isEmpty(state2)) {
                    mState2.setError("State Required!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //booking via sms
                String ourNumber="8356951424";
                if(checkPermission(Manifest.permission.SEND_SMS)){
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(ourNumber,null,"id:"+phoneno+"\nPick"+"\n"+firstname+"\n"+phoneno+"\n"+pincode+"\n"+flatno+"\n"+area+"\n"+landmark+"\n"+town+"\n"+state,null,null);
                    smsManager.sendTextMessage(ourNumber,null,"id:"+phoneno+"\nDrop"+"\n"+firstname2+"\n"+phoneno2+"\n"+pincode2+"\n"+flatno2+"\n"+area2+"\n"+landmark2+"\n"+town2+"\n"+state2,null,null);
                    Toast.makeText(smsbooking.this, "Msg Sent!\nWe will call you soon\nCheck your sent messages for more!", Toast.LENGTH_LONG).show();

                    //notification
                    Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    PendingIntent pi = PendingIntent.
                            getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_ONE_SHOT);
                    String Channel_Id="Default";
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),Channel_Id);
                    builder.setSmallIcon(R.drawable.ic_notify)
                            .setContentTitle("Sms Booking Successful!")
                            .setContentText("You will be contacted soon.")
                            .setAutoCancel(true)
                            .setContentIntent(pi);
                    NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                    {
                        NotificationChannel channel=new NotificationChannel(
                                Channel_Id,"Default channel",NotificationManager.IMPORTANCE_DEFAULT);
                        manager.createNotificationChannel(channel);
                    }
                    manager.notify(0,builder.build());

                    //above for notification



                    startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                    progressBar.setVisibility(View.INVISIBLE);


                }else{
                    Toast.makeText(smsbooking.this, "Msg not sent!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }

                //

            }
            });


    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return(check == PackageManager.PERMISSION_GRANTED);

    }
}