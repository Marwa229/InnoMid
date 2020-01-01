package com.example.innomid.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.innomid.BottomNavigationBehavior;
import com.example.innomid.CropCircleTransformation;
import com.example.innomid.Fragments.FragmentAppointments;
import com.example.innomid.Fragments.FragmentLocation;
import com.example.innomid.Fragments.FragmentLocationEmpty;
import com.example.innomid.Fragments.FragmentNotifications;
import com.example.innomid.Fragments.FragmentPatientPortal;
import com.example.innomid.Fragments.FragmentPersonalinfo;
import com.example.innomid.Fragments.FragmentServices;
import com.example.innomid.Fragments.FragmentSettings;
import com.example.innomid.Fragments.HomeFragment;
import com.example.innomid.LocaleHelper;
import com.example.innomid.R;
import com.example.innomid.Utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int UPDATE_DATA = 0;
    private static final int ADD_NEW = 1;
    private static final int code = 101;
    public Uri selectedImage;
    static android.app.AlertDialog.Builder builder;
    CropCircleTransformation circleTransformation;
    View hView;
    private DrawerLayout drawer;
    public static Toolbar toolbar;
    private Fragment fragment;
    public static BottomNavigationView navigation;
    private NavigationView navigationView;
    private ConstraintLayout layout;
    private static AlertDialog alertDialog;
    private Locale localeNew = null;
    private ImageView imageView;
    private TextView nameprof;
    private TextView phoneprof;
    private static boolean mTwoPane;



    //buttom tabed view actions
    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle(R.string.title_home);
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    navigation.getMenu().findItem(R.id.navigation_home).setChecked(true);

                    return false;
                case R.id.navigation_loca:
                    toolbar.setTitle(R.string.location);
                    fragment = new FragmentLocation();
                    loadFragment(fragment);
                    /// showLocationConfirmationDialog();
                    return true;
                case R.id.navigation_dashboard:
                    toolbar.setTitle(R.string.Appointments);
                    fragment = new FragmentAppointments();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_notifications:
                     toolbar.setTitle(R.string.title_notifications);
                     showNotificationConfirmationDialog();
                    return true;
                case R.id.navigation_profile:
                    toolbar.setTitle(R.string.personal_informations);
                    fragment = new FragmentPersonalinfo();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {

        if(navigation.getSelectedItemId() != R.id.navigation_home |navigationView.getMenu().findItem(R.id.nav_settings).isChecked()

                | navigationView.getMenu().findItem(R.id.nav_language).isChecked()
                | navigationView.getMenu().findItem(R.id.nav_share).isChecked()
                | navigationView.getMenu().findItem(R.id.nav_signout).isChecked() ){
//            fragment = new HomeFragment();
//            loadFragment(fragment);
            getFragmentManager().popBackStack();
            navigationView.getMenu().findItem(R.id.nav_language).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_signout).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_share).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_settings).setChecked(false);
            navigation.getMenu().findItem(R.id.navigation_home).setChecked(true);
        }

        else {
            super.onBackPressed();
            finish();
        }
    }

    public static boolean getNoPane() {
        return mTwoPane;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextAppearance(this, R.style.toolbartextapperance);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        initNavigationDrawer(this, toolbar, -1);

        if (findViewById(R.id.tab_list_recipe_container) != null) {
            mTwoPane = true;
        } else {
            mTwoPane = false;
        }



        navigation = findViewById(R.id.nav_view1);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemTextAppearanceActive(R.style.bottomnavtextapperance);
        navigation.setItemTextAppearanceActive(R.style.bottomnavtextapperance);
        toolbar.setTitle(R.string.app_name);
        layout = findViewById(R.id.containerview);
//        ActionBarDrawerToggle toggle;
//        toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
       // toggle.setDrawerIndicatorEnabled(false);
        //toggle.setHomeAsUpIndicator(R.drawable.);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setItemTextAppearance(R.style.navviewtextapperance);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @SuppressLint("ResourceAsColor")
//            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.nav_personal:
//                        toolbar.setTitle(R.string.personal_informations);
//                        navigation.getMenu().findItem(R.id.navigation_profile).setChecked(true);
//                        fragment = new FragmentPersonalinfo();
//                        loadFragment(fragment);
//
//                        break;
//                    case R.id.nav_settings:
//                        toolbar.setTitle(R.string.settings);
//                        fragment = new FragmentSettings();
//                        loadFragment(fragment);
//                        // drawer.setScrimColor(Color.TRANSPARENT);
//                        break;
//                    case R.id.nav_language:
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
//                            showLanguageConfirmationDialog();
//
//                        }
//
//                        break;
//
//                    case R.id.nav_share:
//                        toolbar.setTitle(R.string.share_app_with_friends);
//
//                        break;
//                    case R.id.nav_signout:
//                        toolbar.setTitle(R.string.sign_out);
//
//                        break;
//
//                }
//                drawer.closeDrawer(GravityCompat.START);
//                return true;
//            }
//        });

        circleTransformation = new CropCircleTransformation();
        hView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        imageView = hView.findViewById(R.id.imageView);
        nameprof = hView.findViewById(R.id.nameprof);
        nameprof.setText("marwa");
        phoneprof = hView.findViewById(R.id.phoneprof);
        phoneprof.setText("01154993663");
        Picasso.with(this).load(R.drawable.fish)
                .transform(circleTransformation)
                .into((ImageView) hView.findViewById(R.id.imageView));

        //fragment home
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            HomeFragment homeFragment = new HomeFragment();
            transaction.replace(R.id.tab_list_recipe_container, homeFragment);
            transaction.commit();
            toolbar.setTitle(R.string.title_home);

        }

        if (savedInstanceState != null) {

            selectedImage = savedInstanceState.getParcelable("uri");
            Picasso.with(this).load(selectedImage)
                    .transform(circleTransformation)
                    .into((ImageView) hView.findViewById(R.id.imageView));
        }

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());


    }



    public void GetAppointment(View view) {
        navigation.getMenu().findItem(R.id.navigation_dashboard).setChecked(true);
        fragment = new FragmentAppointments();
        loadFragment(fragment);
        toolbar.setTitle(R.string.Appointments);



    }


    public void GetServices(View view) {
        fragment = new FragmentServices();
        toolbar.setTitle(R.string.Services);
        loadFragment(fragment);
        navigation.getMenu().findItem(R.id.navigation_home).setChecked(false);

    }


    public void GetPatientPortal(View view) {
        fragment=new FragmentPatientPortal();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.tab_list_recipe_container, fragment);
        getFragmentManager().popBackStack();
        transaction.commit();
        toolbar.setTitle(R.string.patientportal);
        navigation.getMenu().findItem(R.id.navigation_home).setChecked(false);
    }




//    //Change Language
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//    public void setLanguage(Context c, String lang) {
//        localeNew = new Locale(lang);
//       // Locale.setDefault(localeNew);
//        Resources res = c.getResources();
//        Configuration newConfig = new Configuration(res.getConfiguration());
//        // newConfig.locale = localeNew;
//        newConfig.setLocale(localeNew);
//        newConfig.setLayoutDirection(localeNew);
//        c.createConfigurationContext(newConfig);
//        DisplayMetrics dm = res.getDisplayMetrics();
//        res.updateConfiguration(newConfig, dm);
//    }


    //save Language State when app closed
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void fetchLanguage() {
        SharedPreferences sharedpreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String lang = sharedpreferences.getString("en", "en");
        //setLanguage(getBaseContext(), lang);


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

    // load fragment
    public  void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.tab_list_recipe_container, fragment);
        // transaction.addToBackStack(null);
         getFragmentManager().popBackStack();
        //transaction.addToBackStack("TAG");

       // transaction.setCustomAnimations(android.R.anim.anticipate_overshoot_interpolator, android.R.anim.anticipate_overshoot_interpolator);
        transaction.commit();


    }

    public void showNotificationConfirmationDialog() {

        builder = new android.app.AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.notification_custom_dialog, null);
        builder.setView(view);
        Button allow = view.findViewById(R.id.allow1);
        Button dontallow = view.findViewById(R.id.dontallow1);
        dontallow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new FragmentLocationEmpty();
                loadFragment(fragment);
                alertDialog.dismiss();
            }
        });
        allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new FragmentNotifications();
                loadFragment(fragment);
                alertDialog.dismiss();
            }
        });
        // Create and show the AlertDialog
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawableResource(
                R.drawable.dialog_body);
        alertDialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case code:
                    //data.getData returns the content URI for the selected Image
                    Uri uriImage = data.getData();
                    //imageView.setImageURI(selectedImage);

                    Picasso.with(this).load(uriImage)
                            .transform(circleTransformation)
                            .into((ImageView) hView.findViewById(R.id.imageView));


                    selectedImage = uriImage;
            }
        }

    }

    //save Locale config changes in rotation
    static class LocaleUtils {

        private static Locale sLocale;

        public static void setLocale(Locale locale) {
            sLocale = locale;
            if (sLocale != null) {
                Locale.setDefault(sLocale);
            }
        }

        static void updateConfig(ContextThemeWrapper wrapper) {
            if (sLocale != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                Configuration configuration = new Configuration();
                configuration.setLocale(sLocale);
                wrapper.applyOverrideConfiguration(configuration);
            }
        }

        static void updateConfig(Application app, Configuration configuration) {
            if (sLocale != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                //Wrapping the configuration to avoid Activity endless loop
                Configuration config = new Configuration(configuration);
                // We must use the now-deprecated config.locale and res.updateConfiguration here,
                // because the replacements aren't available till API level 24 and 17 respectively.
                config.locale = sLocale;
                Resources res = app.getBaseContext().getResources();
                res.updateConfiguration(config, res.getDisplayMetrics());
            }
        }
    }


    @SuppressLint("ResourceAsColor")
    public void showLanguageConfirmationDialog() {
        builder = new android.app.AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.discardchanges_dialog, null);
        builder.setView(view);
        Button yes = view.findViewById(R.id.yaas);
        Button no = view.findViewById(R.id.noo);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   String CurrentLang = Locale.getDefault().getLanguage();
                Locale current = getResources().getConfiguration().locale;
                if(current.getLanguage().equals("ar")){
                    //LocaleHelper.setLocale(getBaseContext(),"en");
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                    finish();
                }else if(current.getLanguage().equals("en")){
                   // LocaleHelper.setLocale(getBaseContext(),"ar");
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                    finish();

                }

                alertDialog.dismiss();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        // Create and show the AlertDialog
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawableResource(
                R.drawable.dialog_body);
        alertDialog.show();
        //  alertDialog.getWindow().setLayout(570, 250);


    }
      void initNavigationDrawer(final Activity activity, Toolbar toolbar, final int activityId) {
        NavigationView navigationView = activity.findViewById(R.id.nav_view);
        final DrawerLayout drawer = activity.findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                NavigationDrawerClicks(activity, id, activityId);

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        hideDrawerItems(activity, activityId);
        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                hideDrawerItems(activity, activityId);
                //showUserInfoNavigationDrawer(activity);
            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private  void NavigationDrawerClicks(final Activity activity, int id, final int activityId) {




        if (activityId == id){ return;
        } else if (id == R.id.nav_personal) {
            toolbar.setTitle(R.string.personal_informations);
                        navigation.getMenu().findItem(R.id.navigation_profile).setChecked(true);
                        fragment = new FragmentPersonalinfo();
                       loadFragment(fragment);
        }
      else if (id == R.id.nav_settings) {

            toolbar.setTitle(R.string.settings);
                      fragment = new FragmentSettings();
                        loadFragment(fragment);

      }
      else if (id == R.id.nav_signout) {
            activity.finish();
        }
        else if (id == R.id.nav_language) {
            final Locale current = activity.getResources().getConfiguration().locale;
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(R.string.are_you_sure_you_want_to_nchange_language_to_arabic);
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked the "Delete" button, so delete the pet.
                    if(current.getLanguage().equals("ar")){
                        LocaleHelper.setLocale(activity,"en");
                        activity.startActivity(new Intent(activity, activity.getClass()));
                        activity.finish();
                    }else if(current.getLanguage().equals("en")){
                        LocaleHelper.setLocale(activity,"ar");
                        activity.startActivity(new Intent(activity, activity.getClass()));
                        activity.finish();

                    }
                }
            });
            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked the "Cancel" button, so dismiss the dialog
                    // and continue editing the pet.
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    }

    static private void hideDrawerItems(Activity activity, int checkedId) {

    }


}


