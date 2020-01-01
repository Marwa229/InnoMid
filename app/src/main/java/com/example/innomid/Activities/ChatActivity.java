package com.example.innomid.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.innomid.LocaleHelper;
import com.example.innomid.R;

import java.util.Locale;

public class ChatActivity extends AppCompatActivity {




    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Locale locale = new Locale(LocaleHelper.getLanguage(this));
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());


    }

    EditText sendmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        final Locale current = getResources().getConfiguration().locale;
        Toolbar toolbar =  findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        if(current.getLanguage().equals("ar")) {
            toolbar.setNavigationIcon(R.drawable.nextbtn);
        }else if(current.getLanguage().equals("en")){
            toolbar.setNavigationIcon(R.drawable.backbtn);
        }

        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        toolbar.setTitleTextColor(Color.parseColor("#003376"));
        toolbar.setTitleTextAppearance(this,R.style.toolbartextapperance);
        toolbar.setTitle("Doctor Test Name");
        sendmsg=findViewById(R.id.et_message);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(sendmsg, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home/back button
            case android.R.id.home:
                finish();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
