package com.example.innomid.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.innomid.R;

public class ServiceReview extends AppCompatActivity {


//    @Override
//
//    protected void attachBaseContext(Context base) {
//
//        super.attachBaseContext(LocaleHelper.onAttach(base, "ar"));
//
//    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_close);
        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        toolbar.setTitleTextColor(Color.parseColor("#003376"));
        toolbar.setElevation(10f);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
