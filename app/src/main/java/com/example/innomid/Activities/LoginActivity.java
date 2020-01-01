package com.example.innomid.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.innomid.CropCircleTransformation;
import com.example.innomid.R;
import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {
     Button button;
     ImageView imageView;
     TextView textView;
     EditText medicnum;
     EditText editmobile;
     Uri selectedImage;
    CropCircleTransformation circleTransformation;
    String name;
    String phone;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final int code=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        //Retrieving editor
        editor = sharedPreferences.edit();
        circleTransformation=new CropCircleTransformation();
        button=findViewById(R.id.loginbut1);
        imageView=findViewById(R.id.loginimg1);
        textView=findViewById(R.id.registertxt);
        medicnum=findViewById(R.id.loginedit1);
        editmobile=findViewById(R.id.mobilelog);
        Intent intent =getIntent();
        String medicnum1=intent.getStringExtra("medicnum");
        medicnum.setText(medicnum1);
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        final boolean firstStart = prefs.getBoolean("firstStart", true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkTextViews()){
                    return;
                }
                else{

                    if(firstStart){
                    Intent intent= new Intent(LoginActivity.this, HospitalsActivity.class);
                    startActivity(intent);
                        finish();
                    SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("firstStart", false);
                    editor.apply();

                }else {

                        Intent intent= new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }



                }

            }
        });
        //IMAGEVIEW ACTION
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //Create an Intent with action as ACTION_PICK
//                Intent intent=new Intent(Intent.ACTION_PICK);
//                // Sets the type as image/*. This ensures only components of type image are selected
//                intent.setType("image/*");
//                //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
//                String[] mimeTypes = {"image/jpeg", "image/png"};
//                intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
//                // Launching the Intent
//                startActivityForResult(intent,code);
//
//
//
//            }
//        }); +
//        `
        if (savedInstanceState != null){
            selectedImage = savedInstanceState.getParcelable("uri");
            Picasso.with(this).load(selectedImage)
                    .transform(circleTransformation)
                    .into((ImageView) findViewById(R.id.loginimg1));
        }


//        //EDITPASSWORD ACTION
//        String pass = editText.getText().toString();
//        if(TextUtils.isEmpty(pass) || pass.length() < 6)
//        {
//            editText.setError("You must have 6 characters in your password");
//            return;
//        }

    }

//OPEN GALLERY TO SELECT AN IMAGE
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
                            .into((ImageView) findViewById(R.id.loginimg1));


                    selectedImage=uriImage;
            }
        }

    }


    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (selectedImage != null) {
            outState.putParcelable("uri", selectedImage);
        }
    }


    public void Rgister(View view) {

            Intent i =new Intent(this, RegisterActivity.class);
            startActivity(i);
            finish();


    }

    private boolean checkTextViews() {
        boolean success = true;
        if (medicnum.getText().toString().isEmpty()) {
            medicnum.setError(getString(R.string.emedicnum));
            success = false;
        } else
            medicnum.setError(null);
        if (editmobile.getText().toString().isEmpty()) {
            editmobile.setError(getString(R.string.emobnum));
            success = false;
        } else
            editmobile.setError(null);

        return success;
    }



    private class Connection extends AsyncTask<String, Void, Integer>
    {
        private static final int CONNECTION_FAILURE = 0;
        private static final int LOGIN_FAILURE = 1;
        private static final int SUCCESS = 2;

        protected void onPreExecute()
        {
          ///  progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        protected Integer doInBackground(String... strings)
        {
            //ConnectionConfiguration conConfig = new ConnectionConfiguration("192.168.1.66", 5222, "domain");
         //   connection = new XMPPConnection(conConfig);
            try
            {
               // connection.connect();
              //  Log.i("TESTING", "CONNECTED TO " + connection.getHost());
            }
            catch(Exception e)
            {
                Log.e("TESTING", e.getMessage());
                return CONNECTION_FAILURE;
            }
            try
            {
              //  connection.login(strings[0], strings[1]);
              //  Log.i("TESTING", "LOGGED IN AS " + connection.getUser());
               // ContactsContract.Presence presence = new ContactsContract.Presence(ContactsContract.Presence.Type.available);
             //   connection.sendPacket(presence);
            }
            catch(Exception e)
            {
                Log.e("TESTING", e.getMessage());
                return LOGIN_FAILURE;
            }
            return SUCCESS;
        }

        protected void onPostExecute(Integer integer)
        {
           // progressBar.setVisibility(ProgressBar.GONE);
            switch(integer)
            {
                case CONNECTION_FAILURE:
                    Toast.makeText(getApplicationContext(), "Failed to connect to the server.", Toast.LENGTH_LONG).show();
                    break;
                case LOGIN_FAILURE:
                    Toast.makeText(getApplicationContext(), "Please check your user id and or password.", Toast.LENGTH_LONG).show();
                    break;
                case SUCCESS:
                  //  if(rememberMe.isChecked())
                  //  {
                        //Setting value in SharedPrefernces using editor.
                        //editor.putBoolean(getString(R.string.remember_me), rememberMe.isChecked());
                       // editor.putString(getString(R.string.user_id), userId.getText().toString());
                       // editor.putString(getString(R.string.password), password.getText().toString());
                        //Committing the changes.
                        editor.commit();
                   // }
                   // else
                        //Clearing SharedPreferences.
                        sharedPreferences.edit().clear().commit();
                    //startActivity(new Intent(getApplicationContext(), Home.class));

            }
        }
    }



}
