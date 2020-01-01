package com.example.innomid.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.innomid.LocaleHelper;
import com.example.innomid.R;
import com.squareup.picasso.Picasso;
import java.util.Locale;

public class RadiologyResultActivity extends AppCompatActivity {

    private String url;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiology_result);
        Toolbar toolbar =  findViewById(R.id.toolbar2);
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
        toolbar.setTitle("Medical Test Name");

        imageView=findViewById(R.id.radimg);
        url = "https://www.google.com.eg/url?sa=i&url=https%3A%2F%2Fwww.indy100.com%2Farticle%2Fdo-fish-feel-pain-science-study-natural-world-sea-9124611&psig=AOvVaw08EaJIhQrnK1JRX5AeEDAA&ust=1576055188960000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCKjJu6DdquYCFQAAAAAdAAAAABAD";
        Picasso.with(getBaseContext()).load(url).into(imageView);


    }


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home/back button
            case android.R.id.home:
                finish();
                break;

            case R.id.share:
                startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(this)
                        .setType("text/plain")
                        .setText(url)
                        .getIntent(), "Share"));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
