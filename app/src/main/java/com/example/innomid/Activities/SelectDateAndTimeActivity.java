package com.example.innomid.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.innomid.Adapters.DateRecyclarAdapter;
import com.example.innomid.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SelectDateAndTimeActivity extends AppCompatActivity implements DateRecyclarAdapter.DateAdapterInterface {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    int no_of_days = 0;
    List<Date> dates = null;
    RecyclerView recycler_view;
    Calendar calendar = null;


    DateRecyclarAdapter recyAdapter = null;
    LinearLayoutManager linearLayoutManager = null;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date_and_time);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
//        toolbar.setNavigationIcon(R.drawable.ic_action_close);
//        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
//        toolbar.setTitleTextColor(Color.parseColor("#003376"));
//        toolbar.setTitleTextAppearance(this,R.style.toolbartextapperance);
//        toolbar.setTitle("Select Date & Time");


        ActionBar actionBar = getSupportActionBar();
       // actionBar.setLogo(R.drawable.ic_action_close);
      //  actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.selectdate);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_close);
        DateRecyclarAdapter adapter = null;






        recycler_view = (RecyclerView) findViewById(R.id.timerecyclar);

        dates = new ArrayList<>();

       // linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        GridLayoutManager manager=new GridLayoutManager(this,3);
        recycler_view.setLayoutManager(manager);
        no_of_days = getNoOfDays("20-06-2016 12:00", "20-08-2017 12:00");

        calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("dd-MM-yyyy HH:mm").parse("20-06-2016 12:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < no_of_days; i++) {
            calendar.add(Calendar.DATE, 10);
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            } else {
                dates.add(calendar.getTime());
            }
        }

        recyAdapter = new DateRecyclarAdapter(SelectDateAndTimeActivity.this,dates,SelectDateAndTimeActivity.this);
        recycler_view.setAdapter(recyAdapter);

    }

    public int getNoOfDays(String Created_date_String, String Expire_date_String) {

        Date Created_convertedDate = null, Expire_CovertedDate = null, todayWithZeroTime = null;
        try {
            Created_convertedDate = DATE_FORMAT.parse(Created_date_String);
            Expire_CovertedDate = DATE_FORMAT.parse(Expire_date_String);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar createdDay = Calendar.getInstance();
        createdDay.setTime(Created_convertedDate);

        Calendar expireDay = Calendar.getInstance();
        expireDay.setTime(Expire_CovertedDate);

        long diff = createdDay.getTimeInMillis() - expireDay.getTimeInMillis();

        long days = (diff / (24 * 60 * 60 * 1000));

        return (Math.abs((int) days))/10;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home/back button
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void OnclicDate(int position) {
        recycler_view.setClickable(false);

    }
}
