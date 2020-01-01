package com.example.innomid.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.innomid.R;

import java.util.Locale;
import java.util.Random;

public class VarifacationActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 0;
    EditText code1;
    EditText code2;
    EditText code3;
    EditText code4;
    ImageView valid;
    TextView timer;
    TextView resend;
    TextView getmobile;
    CountDownTimer countDownTimer;
    String num;
    String val1;
    String val2;
    String val3;
    String val4;
    Random rNo;
    int code;
    String mobi;


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home/back button
            case android.R.id.home:
                Intent intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varifacation);
      //  LocaleHelper.onCreate(this);
        code1 = findViewById(R.id.code1);
        code2 = findViewById(R.id.code2);
        code3 = findViewById(R.id.code3);
        code4 = findViewById(R.id.code4);
        valid = findViewById(R.id.verfyimg);
        timer = findViewById(R.id.Timer);
        resend = findViewById(R.id.resend);
        getmobile=findViewById(R.id.mobile_verf);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        final Locale current = getResources().getConfiguration().locale;
        if(current.getLanguage().equals("ar")){
            toolbar.setNavigationIcon(R.drawable.nextbtn);
        }else if(current.getLanguage().equals("en")){
            toolbar.setNavigationIcon(R.drawable.backbtn);
        }

        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        toolbar.setTitleTextColor(Color.parseColor("#003376"));
        toolbar.setTitleTextAppearance(this,R.style.toolbartextapperance);

        //Show Keyboard Automatically
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(code1, InputMethodManager.SHOW_IMPLICIT);


        final Intent intent=getIntent();
        mobi=intent.getStringExtra("num");
        getmobile.setText(mobi);
        //create rondom 4 code to check phone+ num
        rNo = new Random();
        code = rNo.nextInt((9999 - 1000) + 1) + 1000;
        num= String.valueOf(code);


        //check sdk permissions to send sms
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }

        //send sms message to check phone number

        if(!TextUtils.isEmpty(num) && !TextUtils.isEmpty(mobi)) {
            if(checkPermission()) {

//Get the default SmsManager//

               SmsManager smsManager = SmsManager.getDefault();
                Toast.makeText(this, num, Toast.LENGTH_SHORT).show();

//Send the SMS//

           //    smsManager.sendTextMessage(mobi, null, num, null, null);
            }else {
                Toast.makeText(VarifacationActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }


        //Move Automatically between Edittexts
        code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {

                if (s.length() == 1) {
                    code2.requestFocus();
                }

            }

        });

        code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    code3.requestFocus();
                }

            }

        });
        code3.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    code4.requestFocus();
                }


            }

        });

        code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                 code1.requestFocus();

                if (val1.equals(code1.getText().toString()) && val2.equals(code2.getText().toString()) && val3.equals(code3.getText().toString()) && val4.equals(code4.getText().toString())) {

                    Toast.makeText(VarifacationActivity.this, "Phone Verified Successfly", Toast.LENGTH_SHORT).show();
                    valid.setVisibility(View.VISIBLE);
                    timer.setVisibility(View.GONE);
                    Intent intent2 = new Intent(VarifacationActivity.this, LoginActivity.class);
                     Random rNo1 = new Random();
                    int codem = rNo1.nextInt((999999 - 100000) + 1) + 10000;
                    String num1=String.valueOf(codem);
                    intent2.putExtra("medicnum",num1);
                    startActivity(intent2);
                    finish();

                }  else {

                    Toast.makeText(VarifacationActivity.this, "Phone Verified  Not Successfly", Toast.LENGTH_SHORT).show();
                    valid.setVisibility(View.GONE);
                    code1.getText().clear();
                    code2.getText().clear();
                    code3.getText().clear();
                    code4.getText().clear();


                }

            }
        });

        val1 = String.valueOf(num.charAt(0));
        val2 = String.valueOf(num.charAt(1));
        val3 = String.valueOf(num.charAt(2));
        val4 = String.valueOf(num.charAt(3));
        countDownTimer = new CountDownTimer(Integer.parseInt(String.format("%d",60000)),Integer.parseInt(String.format("%d",1000))) {

            public void onTick(long millisUntilFinished) {


                String myIntAsString = String.format("%d", millisUntilFinished / 1000);
                timer.setText(getString(R.string.zero)+myIntAsString);
                //here you can have your logic to set text to edittext
                resend.setEnabled(false);
            }

            public void onFinish() {
                timer.setText(" ");
                resend.setEnabled(true);
                resend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        countDownTimer.start();
                        rNo = new Random();
                        code = rNo.nextInt((9999 - 1000) + 1) + 1000;
                        num = String.valueOf(code);
                        SmsManager sms = SmsManager.getDefault();
                       // sms.sendTextMessage(mobi,null,num,null,null);
                        Toast.makeText(VarifacationActivity.this, num, Toast.LENGTH_SHORT).show();
                        val1 = String.valueOf(num.charAt(0));
                        val2 = String.valueOf(num.charAt(1));
                        val3 = String.valueOf(num.charAt(2));
                        val4 = String.valueOf(num.charAt(3));


                    }
                });
            }
        };


        countDownTimer.start();


        //code verifacation
        code4.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


                return false;
            }
        });


    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);

    }




    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this,
                            "Permission accepted", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this,
                            "Permission denied", Toast.LENGTH_LONG).show();
                    // sendSMS.setEnabled(false);

                }
                break;
        }
    }



}
