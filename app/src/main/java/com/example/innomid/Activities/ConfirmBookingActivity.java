package com.example.innomid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.innomid.R;

import static com.example.innomid.Activities.MainActivity.builder;

public class ConfirmBookingActivity extends AppCompatActivity {
    Button confbook;
    TextView stnewbook;
    static android.app.AlertDialog.Builder builder;
    private static AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_booking);
        confbook=findViewById(R.id.confbook);
        stnewbook=findViewById(R.id.startnewbook);
        confbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConfirmBooking();

            }
        });

        stnewbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartNewBokking();
            }
        });

    }


    public void ConfirmBooking(){
        builder = new android.app.AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.confirm_custom_dialog, null);
        builder.setView(view);
        alertDialog = builder.create();
        Button done=view.findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
                finish();
            }
        });
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawableResource(
                R.drawable.dialog_body);
        alertDialog.show();
    }

    public void StartNewBokking(){
        builder = new android.app.AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.discardchanges_dialog, null);
        Button no=view.findViewById(R.id.noo);
        Button yes=view.findViewById(R.id.yaas);
        builder.setView(view);
        alertDialog = builder.create();
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawableResource(
                R.drawable.dialog_body);
        alertDialog.show();


    }





}
