package com.example.innomid.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.innomid.CropCircleTransformation;
import com.example.innomid.R;
import com.squareup.picasso.Picasso;

public class RegisterActivity extends AppCompatActivity {



    TextView textView;
    ImageView imageView;
    EditText identitynum;
    EditText mobile;
    EditText fullname;
    Button registbutton;
    private static final int code = 101;
    public Uri selectedImage;
    CropCircleTransformation circleTransformation;
    private boolean HasChanged = false;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            HasChanged = true;
            return false;
        }
    };




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        circleTransformation = new CropCircleTransformation();
        identitynum = findViewById(R.id.identnum);
        identitynum.setOnTouchListener(mTouchListener);
        mobile = findViewById(R.id.mobile);
        mobile.setOnTouchListener(mTouchListener);
        fullname = findViewById(R.id.registeditname);
        registbutton = findViewById(R.id.registbut1);
        textView = findViewById(R.id.logback);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        imageView = findViewById(R.id.registimg1);
        imageView.setOnTouchListener(mTouchListener);

        fullname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fullname.setOnTouchListener(mTouchListener);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        registbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkViews()) {
                    return;
                } else {

                    Intent intent = new Intent(RegisterActivity.this, VarifacationActivity.class);
                    String num=mobile.getText().toString();
                    intent.putExtra("num",num);
                    startActivity(intent);
                    finish();
                }

            }
        });


        if (savedInstanceState != null) {
            selectedImage = savedInstanceState.getParcelable("uri");
            Picasso.with(this).load(selectedImage)
                    .transform(circleTransformation)
                    .into((ImageView) findViewById(R.id.registimg1));

        }

    }

    public void Loginback(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();


    }


    public void SelectiMg(View view) {

        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case code:
                    Uri uriImage = data.getData();
                    Picasso.with(this).load(uriImage)
                            .transform(circleTransformation)
                            .into((ImageView) findViewById(R.id.registimg1));


                    selectedImage = uriImage;
            }
        }

    }


    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (selectedImage != null) {
            outState.putParcelable("uri", selectedImage);

        }
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void onBackPressed() {
        if (!HasChanged) {
            super.onBackPressed();
            Intent intent=new Intent(getBaseContext(),LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            DialogInterface.OnClickListener discardButtonClickListener =
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent=new Intent(getBaseContext(),LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    };
            showUnsavedChangesDialog(discardButtonClickListener);
        }
    }

    private boolean checkViews() {
        boolean success = true;

        if (mobile.getText().toString().isEmpty() || mobile.getText().length()<9) {
            mobile.setError(getString(R.string.emobnum));
            success = false;
        }else {
            mobile.setError(null);
        }

        if (identitynum.getText().toString().isEmpty()||identitynum.getText().length()<10) {
            identitynum.setError("Please Enter a Valid Identity Number");
            success = false;
        }
        else {
            identitynum.setError(null);

        }

        if (fullname.getText().toString().isEmpty())
            fullname.setError(getString(R.string.efullname));

        return success;
    }


}



